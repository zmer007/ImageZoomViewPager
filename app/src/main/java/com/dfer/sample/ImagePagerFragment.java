package com.dfer.sample;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;

import com.bumptech.glide.Glide;

import it.sephiroth.android.library.imagezoom.ImageViewTouch;

public class ImagePagerFragment extends Fragment {
    private static final String TAG = "ImagePagerFragment";

    private static final String IMAGE_POSITION = "imgPosition";
    ImageViewTouch mImageViewTouch;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_zoom_image, container, false);

        int imagePosition = getArguments().getInt(IMAGE_POSITION);

        mImageViewTouch = (ImageViewTouch) v.findViewById(R.id.image_zoom);
        Glide.with(this).load(MockData.data.get(imagePosition)).asBitmap().fitCenter().into(mImageViewTouch);
        mImageViewTouch.setTag(ZoomViewPagerActivity.VIEW_PAGER_OBJECT_TAG + imagePosition);

        return v;
    }

    public boolean isZoomed() {
        return mImageViewTouch.isZoomed;
    }

    public void setImageTranslationY(float translationY) {
        mImageViewTouch.setTranslationY(translationY);
    }

    public void animTranslationY(int duration, float translationY) {
        mImageViewTouch.animate().translationY(translationY).setInterpolator(new LinearInterpolator()).setDuration(duration).start();
    }

    public int getImageHeight() {
        return mImageViewTouch.getHeight();
    }

    public static ImagePagerFragment newInstance(int position) {
        Bundle args = new Bundle();
        args.putInt(IMAGE_POSITION, position);
        ImagePagerFragment ipf = new ImagePagerFragment();
        ipf.setArguments(args);
        return ipf;
    }
}

package com.dfer.sample;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import it.sephiroth.android.library.imagezoom.ImageViewTouch;

public class ImagePagerFragment extends Fragment {
    private static final String TAG = "ImagePagerFragment";

    private static final String IMAGE_POSITION = "imgPosition";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_zoom_image, container, false);

        int imagePosition = getArguments().getInt(IMAGE_POSITION);

        ImageViewTouch mImageViewTouch = (ImageViewTouch) v.findViewById(R.id.image_zoom);
        mImageViewTouch.setImageResource(MockData.data.get(imagePosition));
        mImageViewTouch.setTag(ZoomViewPagerActivity.VIEW_PAGER_OBJECT_TAG + imagePosition);

        return v;
    }

    public static ImagePagerFragment newInstance(int position) {
        Bundle args = new Bundle();
        args.putInt(IMAGE_POSITION, position);
        ImagePagerFragment ipf = new ImagePagerFragment();
        ipf.setArguments(args);
        return ipf;
    }
}

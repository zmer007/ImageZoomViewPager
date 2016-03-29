package com.dfer.sample;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.SimpleOnPageChangeListener;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import it.sephiroth.android.library.imagezoom.ImageViewTouch;

public class ZoomViewPagerActivity extends AppCompatActivity {

    private static final String TAG = "ImagePagerActivity";
    public static final String VIEW_PAGER_OBJECT_TAG = "com.dfer.mygallerylib.image#";

    private int previousPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ImageViewPager viewPager = new ImageViewPager(this);
        viewPager.setId(R.id.viewPager);
        setContentView(viewPager);

        FragmentManager fm = getSupportFragmentManager();
        viewPager.setAdapter(new FragmentStatePagerAdapter(fm) {
            @Override
            public Fragment getItem(int position) {
                return ImagePagerFragment.newInstance(position);
            }

            @Override
            public int getCount() {
                return MockData.data.size();
            }
        });

        viewPager.addOnPageChangeListener(new SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == ViewPager.SCROLL_STATE_SETTLING && previousPosition != viewPager.getCurrentItem()) {
                    try {
                        ImageViewTouch imageViewTouch = (ImageViewTouch)
                                viewPager.findViewWithTag(VIEW_PAGER_OBJECT_TAG + viewPager.getCurrentItem());
                        if (imageViewTouch != null) {
                            imageViewTouch.zoomTo(1f, 250);
                        }

                        previousPosition = viewPager.getCurrentItem();
                    } catch (ClassCastException ex) {
                        Log.e(TAG, "This view pager should have only ImageViewTouch as a children.", ex);
                    }
                }
            }
        });
    }

}

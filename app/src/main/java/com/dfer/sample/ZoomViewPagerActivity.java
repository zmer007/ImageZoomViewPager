package com.dfer.sample;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.SimpleOnPageChangeListener;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;

public class ZoomViewPagerActivity extends FragmentActivity {

    private static final String TAG = "ImagePagerActivity";
    public static final String VIEW_PAGER_OBJECT_TAG = "com.dfer.mygallerylib.image#";
    ImageViewPager mViewPager;
    FragmentStatePagerAdapter adapter;
    View actionBar;
    View bottomBar;
    View backgroundView;
    ImagePagerFragment fragment;
    GestureDetectorCompat mGestureDetectorCompat;

    int maxScroll;
    final int DURATION = 200;

    float currentScrolled;

    boolean viewPageIdle = true;
    GestureDetector.SimpleOnGestureListener mGestureListener = new SimpleOnGestureListener() {
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            if (e2.getPointerCount() > 1 || !viewPageIdle) {
                return super.onScroll(e1, e2, distanceX, distanceY);
            }
            currentScrolled = e2.getY() - e1.getY();
            fragment = (ImagePagerFragment) adapter.getItem(mViewPager.getCurrentItem());
            if (Math.abs(distanceY) > Math.abs(distanceX) && !fragment.isZoomed()) {
                mViewPager.setPagingEnabled(false);
                fragment.setImageTranslationY(e2.getY() - e1.getY());
                if (currentScrolled < 0) {
                    actionBar.setTranslationY(e2.getY() - e1.getY());
                    bottomBar.setTranslationY(e1.getY() - e2.getY());
                } else {
                    actionBar.setTranslationY(e1.getY() - e2.getY());
                    bottomBar.setTranslationY(e2.getY() - e1.getY());
                }
                backgroundView.setAlpha(1 - Math.abs(currentScrolled / maxScroll / 2));
                return true;
            }
            return super.onScroll(e1, e2, distanceX, distanceY);
        }

    };

    Fragment[] fragments = new Fragment[MockData.data.size()];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);
        mViewPager = (ImageViewPager) findViewById(R.id.viewPager);
        actionBar = findViewById(R.id.actionBar);
        bottomBar = findViewById(R.id.bottomBar);
        backgroundView = findViewById(R.id.backgroundView);
        FragmentManager fm = getSupportFragmentManager();
        maxScroll = getResources().getDisplayMetrics().heightPixels / 4;
        mGestureDetectorCompat = new GestureDetectorCompat(this, mGestureListener);
        for (int i = 0; i < MockData.data.size(); i++) {
            fragments[i] = ImagePagerFragment.newInstance(i);
        }
        adapter = new FragmentStatePagerAdapter(fm) {
            @Override
            public Fragment getItem(int position) {
                return fragments[position];
            }

            @Override
            public int getCount() {
                return fragments.length;
            }
        };
        mViewPager.setAdapter(adapter);
        mViewPager.addOnPageChangeListener(new SimpleOnPageChangeListener() {

            @Override
            public void onPageScrollStateChanged(int state) {
                viewPageIdle = state == ViewPager.SCROLL_STATE_IDLE;
                Log.d(TAG, "onPageScrollStateChanged: " + viewPageIdle);
            }
        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        mGestureDetectorCompat.onTouchEvent(ev);
        boolean result = super.dispatchTouchEvent(ev);
        if (ev.getAction() == MotionEvent.ACTION_UP && Math.abs(currentScrolled) > 0.001) {
            mViewPager.setPagingEnabled(true);
            if (Math.abs(currentScrolled) < maxScroll) {
                actionBar.animate().translationY(0).setInterpolator(new LinearInterpolator()).setDuration(DURATION).start();
                bottomBar.animate().translationY(0).setInterpolator(new LinearInterpolator()).setDuration(DURATION).start();
                fragment.animTranslationY(DURATION, 0);
                backgroundView.animate().alpha(1).setInterpolator(new LinearInterpolator()).setDuration(DURATION).start();
            } else {
                actionBar.animate().setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        finish();
                    }
                }).translationY(-actionBar.getHeight()).setInterpolator(new LinearInterpolator()).setDuration(DURATION).start();
                backgroundView.animate().alpha(0).setInterpolator(new LinearInterpolator()).setDuration(DURATION).start();
                bottomBar.animate().translationY(bottomBar.getHeight()).setInterpolator(new LinearInterpolator()).setDuration(DURATION).start();
                fragment.animTranslationY(DURATION, currentScrolled > 0 ? imageTranslationY() : -imageTranslationY());
            }
        }
        return result;
    }

    int imageTranslationY() {
        return getResources().getDisplayMetrics().heightPixels / 2 + fragment.getImageHeight() / 2;
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0, 0);
    }

    public void closeClick(View view) {
        finish();
    }
}

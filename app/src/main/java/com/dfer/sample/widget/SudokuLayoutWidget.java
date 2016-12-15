package com.dfer.sample.widget;

import android.content.Context;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

import com.dfer.sample.R;

public final class SudokuLayoutWidget extends FrameLayout implements OnClickListener, OnLongClickListener, OnTouchListener {
    private static final String TAG = "SudokuLayoutWidget";

    private int mViewsSpace = 0;
    private int mImageViewWidth = 0;

    private int mGetImageSize;
    private final ImageView[] mImageViews = new ImageView[9];

    private ItemsLayoutType mQuantity = ItemsLayoutType.NORMAL;

    private OnSudokuItemListener mSudokuListener;

    public SudokuLayoutWidget(Context context) {
        this(context, null);
    }

    public SudokuLayoutWidget(Context context, AttributeSet attrs) {
        super(context, attrs);

        int[] imageViewId = new int[]{
                R.id.sudokuWidgetImage0, R.id.sudokuWidgetImage1, R.id.sudokuWidgetImage2,
                R.id.sudokuWidgetImage3, R.id.sudokuWidgetImage4, R.id.sudokuWidgetImage5,
                R.id.sudokuWidgetImage6, R.id.sudokuWidgetImage7, R.id.sudokuWidgetImage8
        };

        for (int i = 0; i < imageViewId.length; i++) {
            mImageViews[i] = new ImageView(context);
            mImageViews[i].setId(imageViewId[i]);
            mImageViews[i].setScaleType(ScaleType.CENTER_CROP);
            mImageViews[i].setVisibility(View.GONE);
            mImageViews[i].setOnClickListener(this);
            mImageViews[i].setOnTouchListener(this);
            mImageViews[i].setOnLongClickListener(this);
            addView(mImageViews[i]);
        }

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        switch (mQuantity) {
            case ONLY_ONE:
                final int oWidth = mImageViewWidth * 2 + mViewsSpace;
                LayoutParams params = new LayoutParams(oWidth, oWidth);
                mImageViews[0].setLayoutParams(params);
                mImageViews[0].setImageResource(R.drawable.a);
                mImageViews[0].setVisibility(View.VISIBLE);
                break;
            case JUST_FOUR:
                LayoutParams params0 = new LayoutParams(mImageViewWidth, mImageViewWidth);
                mImageViews[0].setLayoutParams(params0);
                mImageViews[0].setImageResource(R.drawable.a);
                mImageViews[0].setVisibility(View.VISIBLE);

                LayoutParams params1 = new LayoutParams(mImageViewWidth, mImageViewWidth);
                params1.leftMargin = mImageViewWidth + mViewsSpace;
                mImageViews[1].setLayoutParams(params1);
                mImageViews[1].setImageResource(R.drawable.b);
                mImageViews[1].setVisibility(View.VISIBLE);

                LayoutParams params2 = new LayoutParams(mImageViewWidth, mImageViewWidth);
                params2.topMargin = mImageViewWidth + mViewsSpace;
                mImageViews[2].setLayoutParams(params2);
                mImageViews[2].setImageResource(R.drawable.c);
                mImageViews[2].setVisibility(View.VISIBLE);

                LayoutParams params3 = new LayoutParams(mImageViewWidth, mImageViewWidth);
                params3.topMargin = mImageViewWidth + mViewsSpace;
                params3.leftMargin = mImageViewWidth + mViewsSpace;
                mImageViews[3].setLayoutParams(params3);
                mImageViews[3].setImageResource(R.drawable.d);
                mImageViews[3].setVisibility(View.VISIBLE);
                break;
            case NORMAL:
                switch (mGetImageSize) {
                    case 2:
                        relayout2ImageViews();
                        break;
                    case 3:
                        relayout3ImageViews();
                        break;
                    case 5:
                        relayout5ImageViews();
                        break;
                    case 6:
                        relayout6ImageViews();
                        break;
                    case 7:
                        relayout7ImageViews();
                        break;
                    case 8:
                        relayout8ImageViews();
                        break;
                    default:
                        relayout9ImageViews();
                        break;
                }
                break;
        }

    }

    private void relayout2ImageViews() {
        LayoutParams p0 = new LayoutParams(mImageViewWidth, mImageViewWidth);
        mImageViews[0].setLayoutParams(p0);
        mImageViews[0].setImageResource(R.drawable.a);
        mImageViews[0].setVisibility(View.VISIBLE);

        LayoutParams p1 = new LayoutParams(mImageViewWidth, mImageViewWidth);
        p1.leftMargin = mImageViewWidth + mViewsSpace;
        mImageViews[1].setLayoutParams(p1);
        mImageViews[1].setImageResource(R.drawable.b);
        mImageViews[1].setVisibility(View.VISIBLE);
    }

    private void relayout3ImageViews() {
        relayout2ImageViews();

        LayoutParams p2 = new LayoutParams(mImageViewWidth, mImageViewWidth);
        p2.leftMargin = 2 * (mImageViewWidth + mViewsSpace);
        mImageViews[2].setLayoutParams(p2);
        mImageViews[2].setImageResource(R.drawable.c);
        mImageViews[2].setVisibility(View.VISIBLE);
    }

    private void relayout5ImageViews() {
        relayout3ImageViews();
        
        LayoutParams p3 = new LayoutParams(mImageViewWidth, mImageViewWidth);
        p3.topMargin = mImageViewWidth + mViewsSpace;
        mImageViews[3].setLayoutParams(p3);
        mImageViews[3].setImageResource(R.drawable.d);
        mImageViews[3].setVisibility(View.VISIBLE);

        LayoutParams p4 = new LayoutParams(mImageViewWidth, mImageViewWidth);
        p4.topMargin = mImageViewWidth + mViewsSpace;
        p4.leftMargin = mImageViewWidth + mViewsSpace;
        mImageViews[4].setLayoutParams(p4);
        mImageViews[4].setImageResource(R.mipmap.ic_launcher);
        mImageViews[4].setVisibility(View.VISIBLE);

    }

    private void relayout6ImageViews() {
        relayout5ImageViews();
        
        LayoutParams p5 = new LayoutParams(mImageViewWidth, mImageViewWidth);
        p5.topMargin = mImageViewWidth + mViewsSpace;
        p5.leftMargin = 2*(mImageViewWidth + mViewsSpace);
        mImageViews[5].setLayoutParams(p5);
        mImageViews[5].setImageResource(R.mipmap.ic_launcher);
        mImageViews[5].setVisibility(View.VISIBLE);
    }

    private void relayout7ImageViews() {
        relayout6ImageViews();

        LayoutParams p6 = new LayoutParams(mImageViewWidth, mImageViewWidth);
        p6.topMargin =2 * (mImageViewWidth + mViewsSpace) ;
        mImageViews[6].setLayoutParams(p6);
        mImageViews[6].setImageResource(R.mipmap.ic_launcher);
        mImageViews[6].setVisibility(View.VISIBLE);
    }

    private void relayout8ImageViews() {
        relayout7ImageViews();

        LayoutParams p7 = new LayoutParams(mImageViewWidth, mImageViewWidth);
        p7.topMargin = 2 * (mImageViewWidth + mViewsSpace);
        p7.leftMargin = mImageViewWidth + mViewsSpace;
        mImageViews[7].setLayoutParams(p7);
        mImageViews[7].setImageResource(R.mipmap.ic_launcher);
        mImageViews[7].setVisibility(View.VISIBLE);
    }

    private void relayout9ImageViews() {
        relayout8ImageViews();

        LayoutParams p8 = new LayoutParams(mImageViewWidth, mImageViewWidth);
        p8.topMargin = 2 * (mImageViewWidth + mViewsSpace);
        p8.leftMargin = 2 * (mImageViewWidth + mViewsSpace);
        mImageViews[8].setLayoutParams(p8);
        mImageViews[8].setImageResource(R.mipmap.ic_launcher);
        mImageViews[8].setVisibility(View.VISIBLE);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int layoutWidth = MeasureSpec.getSize(widthMeasureSpec);
        if (layoutWidth != 0) {
            mViewsSpace = layoutWidth / 50;
            mImageViewWidth = (layoutWidth - mViewsSpace * 2) / 3;
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                ImageView view = (ImageView) v;
                view.getDrawable().setColorFilter(0x66000000, PorterDuff.Mode.SRC_ATOP);
                view.invalidate();
                break;
            }
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL: {
                ImageView view = (ImageView) v;
                view.getDrawable().clearColorFilter();
                view.invalidate();
                break;
            }
        }

        return false;
    }

    @Override
    public void onClick(View v) {
        if (mSudokuListener == null) {
            return;
        }
        switch (v.getId()) {
            case R.id.sudokuWidgetImage0:
                mSudokuListener.onItemClickListener(v, 0);
                break;
            case R.id.sudokuWidgetImage1:
                mSudokuListener.onItemClickListener(v, 1);
                break;
            case R.id.sudokuWidgetImage2:
                mSudokuListener.onItemClickListener(v, 2);
                break;
            case R.id.sudokuWidgetImage3:
                mSudokuListener.onItemClickListener(v, 3);
                break;
            case R.id.sudokuWidgetImage4:
                mSudokuListener.onItemClickListener(v, 4);
                break;
            case R.id.sudokuWidgetImage5:
                mSudokuListener.onItemClickListener(v, 5);
                break;
            case R.id.sudokuWidgetImage6:
                mSudokuListener.onItemClickListener(v, 6);
                break;
            case R.id.sudokuWidgetImage7:
                mSudokuListener.onItemClickListener(v, 7);
                break;
            case R.id.sudokuWidgetImage8:
                mSudokuListener.onItemClickListener(v, 8);
                break;
        }
    }

    @Override
    public boolean onLongClick(View v) {
        if (mSudokuListener == null) {
            return true;
        }
        switch (v.getId()) {
            case R.id.sudokuWidgetImage0:
                mSudokuListener.onItemLongClickListener(v, 0);
                break;
            case R.id.sudokuWidgetImage1:
                mSudokuListener.onItemLongClickListener(v, 1);
                break;
            case R.id.sudokuWidgetImage2:
                mSudokuListener.onItemLongClickListener(v, 2);
                break;
            case R.id.sudokuWidgetImage3:
                mSudokuListener.onItemLongClickListener(v, 3);
                break;
            case R.id.sudokuWidgetImage4:
                mSudokuListener.onItemLongClickListener(v, 4);
                break;
            case R.id.sudokuWidgetImage5:
                mSudokuListener.onItemLongClickListener(v, 5);
                break;
            case R.id.sudokuWidgetImage6:
                mSudokuListener.onItemLongClickListener(v, 6);
                break;
            case R.id.sudokuWidgetImage7:
                mSudokuListener.onItemLongClickListener(v, 7);
                break;
            case R.id.sudokuWidgetImage8:
                mSudokuListener.onItemLongClickListener(v, 8);
                break;
        }
        return true;
    }

    public interface OnSudokuItemListener {
        void onItemClickListener(View view, int position);

        void onItemLongClickListener(View view, int position);
    }

    private enum ItemsLayoutType {
        ONLY_ONE, JUST_FOUR, NORMAL
    }

    public void setSudokuItemListener(OnSudokuItemListener listener) {
        mSudokuListener = listener;
    }

    public ImageView[] getImageViews(int size) {
        mGetImageSize = size;
        if (size == 1) {
            mQuantity = ItemsLayoutType.ONLY_ONE;
        } else if (size == 4) {
            mQuantity = ItemsLayoutType.JUST_FOUR;
        } else {
            mQuantity = ItemsLayoutType.NORMAL;
        }

        ImageView[] imageViews = new ImageView[size];
        int i = 0;
        for (; i < size; i++) {
            imageViews[i] = mImageViews[i];
        }
        return imageViews;
    }
}

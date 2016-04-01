package com.dfer.sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.dfer.sample.widget.SudokuLayoutWidget;
import com.dfer.sample.widget.SudokuLayoutWidget.OnSudokuItemListener;


public class MainActivity extends AppCompatActivity {
    SudokuLayoutWidget mSudokuLayoutWidget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSudokuLayoutWidget = (SudokuLayoutWidget) findViewById(R.id.main_sudokuLayoutWidget);
        assert mSudokuLayoutWidget != null;
        ImageView[] ivs = mSudokuLayoutWidget.getImageViews(1);
        mSudokuLayoutWidget.setSudokuItemListener(new OnSudokuItemListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                Toast.makeText(MainActivity.this, "onClick item" + position + " : " + view.getLeft() + "-" + view.getTop(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClickListener(View view, int position) {
                Toast.makeText(MainActivity.this, "onLongClick item" + position + " : " + view.getLeft() + "-" + view.getTop(), Toast.LENGTH_SHORT).show();
            }
        });



    }

    public void clickShowDetail(View v) {
        Intent i = new Intent(this, ZoomViewPagerActivity.class);
        startActivity(i);
    }
}

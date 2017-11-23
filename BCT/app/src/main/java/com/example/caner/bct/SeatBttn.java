package com.example.caner.bct;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * Created by caner on 03.06.2017.
 */

public class SeatBttn extends AppCompatButton{
    private static final int[] STATE_FULL = {R.attr.state_full};
    private static final int[] STATE_EMPTY = {R.attr.state_empty};

    private boolean mIsFull= false;
    private boolean mIsEmpty = false;

    public SeatBttn(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setSeatFull(boolean isFull) {mIsFull = isFull;}
    public void setSeatEmpty(boolean isEmpty) {mIsEmpty = isEmpty;}


    @Override
    protected int[] onCreateDrawableState(int extraSpace) {
        final int[] drawableState = super.onCreateDrawableState(extraSpace + 2);
        if (mIsFull) {
            mergeDrawableStates(drawableState, STATE_FULL);
        }
        if (mIsEmpty) {
            mergeDrawableStates(drawableState, STATE_EMPTY);
        }
        return drawableState;
    }

}

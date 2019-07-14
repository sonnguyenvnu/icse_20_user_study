@Override protected void onDraw(Canvas canvas){
  super.onDraw(canvas);
  mCenterIndicatorPath.reset();
  float sizeDiv2=mCursorSize / 2f;
  float sizeDiv3=mCursorSize / 3f;
  mCenterIndicatorPath.moveTo(mMaxOverScrollDistance - sizeDiv2 + getScrollX(),0);
  mCenterIndicatorPath.rLineTo(0,sizeDiv3);
  mCenterIndicatorPath.rLineTo(sizeDiv2,sizeDiv2);
  mCenterIndicatorPath.rLineTo(sizeDiv2,-sizeDiv2);
  mCenterIndicatorPath.rLineTo(0,-sizeDiv3);
  mCenterIndicatorPath.close();
  mMarkPaint.setColor(mHighlightColor);
  canvas.drawPath(mCenterIndicatorPath,mMarkPaint);
  int start=mCenterIndex - mViewScopeSize;
  int end=mCenterIndex + mViewScopeSize + 1;
  start=Math.max(start,-mViewScopeSize * 2);
  end=Math.min(end,mMarkCount + mViewScopeSize * 2);
  if (mCenterIndex == mMaxSelectableIndex) {
    end+=mViewScopeSize;
  }
 else   if (mCenterIndex == mMinSelectableIndex) {
    start-=mViewScopeSize;
  }
  float x=start * mIntervalDis;
  float markHeight=mHeight - mBottomSpace - mCenterTextSize - mTopSpace;
  float smallMarkShrinkY=markHeight * (1 - mMarkRatio) / 2f;
  smallMarkShrinkY=Math.min((markHeight - mMarkWidth) / 2f,smallMarkShrinkY);
  for (int i=start; i < end; i++) {
    float tempDis=mIntervalDis / 5f;
    for (int offset=-2; offset < 3; offset++) {
      float ox=x + offset * tempDis;
      if (i >= 0 && i <= mMarkCount && mCenterIndex == i) {
        int tempOffset=Math.abs(offset);
        if (tempOffset == 0) {
          mMarkPaint.setColor(mHighlightColor);
        }
 else         if (tempOffset == 1) {
          mMarkPaint.setColor(mFadeMarkColor);
        }
 else {
          mMarkPaint.setColor(mMarkColor);
        }
      }
 else {
        mMarkPaint.setColor(mMarkColor);
      }
      if (offset == 0) {
        mMarkPaint.setStrokeWidth(mCenterMarkWidth);
        canvas.drawLine(ox,mTopSpace,ox,mTopSpace + markHeight,mMarkPaint);
      }
 else {
        mMarkPaint.setStrokeWidth(mMarkWidth);
        canvas.drawLine(ox,mTopSpace + smallMarkShrinkY,ox,mTopSpace + markHeight - smallMarkShrinkY,mMarkPaint);
      }
    }
    if (mMarkCount > 0 && i >= 0 && i < mMarkCount) {
      CharSequence temp=mItems.get(i);
      if (mCenterIndex == i) {
        mMarkTextPaint.setColor(mHighlightColor);
        mMarkTextPaint.setTextSize(mCenterTextSize);
        if (!TextUtils.isEmpty(mAdditionCenterMark)) {
          float off=mAdditionCenterMarkWidth / 2f;
          float tsize=mMarkTextPaint.measureText(temp,0,temp.length());
          canvas.drawText(temp,0,temp.length(),x - off,mHeight - mBottomSpace,mMarkTextPaint);
          mMarkTextPaint.setTextSize(mNormalTextSize);
          canvas.drawText(mAdditionCenterMark,x + tsize / 2f,mHeight - mBottomSpace,mMarkTextPaint);
        }
 else {
          canvas.drawText(temp,0,temp.length(),x,mHeight - mBottomSpace,mMarkTextPaint);
        }
      }
 else {
        mMarkTextPaint.setColor(mMarkTextColor);
        mMarkTextPaint.setTextSize(mNormalTextSize);
        canvas.drawText(temp,0,temp.length(),x,mHeight - mBottomSpace,mMarkTextPaint);
      }
    }
    x+=mIntervalDis;
  }
}

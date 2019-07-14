private void tryComputeMaxWidth(){
  if (!mComputeMaxWidth) {
    return;
  }
  int maxTextWidth=0;
  if (mDisplayedValues == null) {
    float maxDigitWidth=0;
    for (int i=0; i <= 9; i++) {
      final float digitWidth=mSelectorWheelPaint.measureText(formatNumberWithLocale(i));
      if (digitWidth > maxDigitWidth) {
        maxDigitWidth=digitWidth;
      }
    }
    int numberOfDigits=0;
    int current=mMaxValue;
    while (current > 0) {
      numberOfDigits++;
      current=current / 10;
    }
    maxTextWidth=(int)(numberOfDigits * maxDigitWidth);
  }
 else {
    for (    String mDisplayedValue : mDisplayedValues) {
      final float textWidth=mSelectorWheelPaint.measureText(mDisplayedValue);
      if (textWidth > maxTextWidth) {
        maxTextWidth=(int)textWidth;
      }
    }
  }
  maxTextWidth+=mInputText.getPaddingLeft() + mInputText.getPaddingRight();
  if (mMaxWidth != maxTextWidth) {
    if (maxTextWidth > mMinWidth) {
      mMaxWidth=maxTextWidth;
    }
 else {
      mMaxWidth=mMinWidth;
    }
    invalidate();
  }
}

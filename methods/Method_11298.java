public void setValue(float min,float max){
  min=min + offsetValue;
  max=max + offsetValue;
  if (min < minValue) {
    throw new IllegalArgumentException("setValue() min < (preset min - offsetValue) . #min:" + min + " #preset min:" + minValue + " #offsetValue:" + offsetValue);
  }
  if (max > maxValue) {
    throw new IllegalArgumentException("setValue() max > (preset max - offsetValue) . #max:" + max + " #preset max:" + maxValue + " #offsetValue:" + offsetValue);
  }
  if (reserveCount > 1) {
    if ((min - minValue) % reserveCount != 0) {
      throw new IllegalArgumentException("setValue() (min - preset min) % reserveCount != 0 . #min:" + min + " #preset min:" + minValue + "#reserveCount:" + reserveCount + "#reserve:" + reserveValue);
    }
    if ((max - minValue) % reserveCount != 0) {
      throw new IllegalArgumentException("setValue() (max - preset min) % reserveCount != 0 . #max:" + max + " #preset min:" + minValue + "#reserveCount:" + reserveCount + "#reserve:" + reserveValue);
    }
    leftSB.currPercent=(min - minValue) / reserveCount * cellsPercent;
    if (mSeekBarMode == 2) {
      rightSB.currPercent=(max - minValue) / reserveCount * cellsPercent;
    }
  }
 else {
    leftSB.currPercent=(min - minValue) / (maxValue - minValue);
    if (mSeekBarMode == 2) {
      rightSB.currPercent=(max - minValue) / (maxValue - minValue);
    }
  }
  if (callback != null) {
    if (mSeekBarMode == 2) {
      callback.onRangeChanged(this,leftSB.currPercent,rightSB.currPercent,false);
    }
 else {
      callback.onRangeChanged(this,leftSB.currPercent,leftSB.currPercent,false);
    }
  }
  invalidate();
}

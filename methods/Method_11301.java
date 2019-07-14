public void setRules(float min,float max,float reserve,int cells){
  if (max <= min) {
    throw new IllegalArgumentException("setRules() max must be greater than min ! #max:" + max + " #min:" + min);
  }
  mMax=max;
  mMin=min;
  if (min < 0) {
    offsetValue=0 - min;
    min=min + offsetValue;
    max=max + offsetValue;
  }
  minValue=min;
  maxValue=max;
  if (reserve < 0) {
    throw new IllegalArgumentException("setRules() reserve must be greater than zero ! #reserve:" + reserve);
  }
  if (reserve >= max - min) {
    throw new IllegalArgumentException("setRules() reserve must be less than (max - min) ! #reserve:" + reserve + " #max - min:" + (max - min));
  }
  if (cells < 1) {
    throw new IllegalArgumentException("setRules() cells must be greater than 1 ! #cells:" + cells);
  }
  cellsCount=cells;
  cellsPercent=1f / cellsCount;
  reserveValue=reserve;
  reservePercent=reserve / (max - min);
  reserveCount=(int)(reservePercent / cellsPercent + (reservePercent % cellsPercent != 0 ? 1 : 0));
  if (cellsCount > 1) {
    if (mSeekBarMode == 2) {
      if (leftSB.currPercent + cellsPercent * reserveCount <= 1 && leftSB.currPercent + cellsPercent * reserveCount > rightSB.currPercent) {
        rightSB.currPercent=leftSB.currPercent + cellsPercent * reserveCount;
      }
 else       if (rightSB.currPercent - cellsPercent * reserveCount >= 0 && rightSB.currPercent - cellsPercent * reserveCount < leftSB.currPercent) {
        leftSB.currPercent=rightSB.currPercent - cellsPercent * reserveCount;
      }
    }
 else {
      if (1 - cellsPercent * reserveCount >= 0 && 1 - cellsPercent * reserveCount < leftSB.currPercent) {
        leftSB.currPercent=1 - cellsPercent * reserveCount;
      }
    }
  }
 else {
    if (mSeekBarMode == 2) {
      if (leftSB.currPercent + reservePercent <= 1 && leftSB.currPercent + reservePercent > rightSB.currPercent) {
        rightSB.currPercent=leftSB.currPercent + reservePercent;
      }
 else       if (rightSB.currPercent - reservePercent >= 0 && rightSB.currPercent - reservePercent < leftSB.currPercent) {
        leftSB.currPercent=rightSB.currPercent - reservePercent;
      }
    }
 else {
      if (1 - reservePercent >= 0 && 1 - reservePercent < leftSB.currPercent) {
        leftSB.currPercent=1 - reservePercent;
      }
    }
  }
  invalidate();
}

public void reset(){
  msgCount=0;
  mIsCanDrag=false;
  isOutOfRang=false;
  disappear=false;
  pointStart.set(startX,startY);
  pointEnd.set(startX,startY);
  setABCDOPoint();
  invalidate();
}

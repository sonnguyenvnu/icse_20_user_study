private boolean beginAndEndAreZero(){
  TimeList begin=getBegin();
  TimeList end=getEnd();
  if (begin.getLength() == 1 && end.getLength() == 1) {
    Time beginTime=begin.item(0);
    Time endTime=end.item(0);
    return beginTime.getOffset() == 0. && endTime.getOffset() == 0.;
  }
  return false;
}

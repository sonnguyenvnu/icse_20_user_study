@Override public int approximateRangeSize(int firstMeasuredItemWidth,int firstMeasuredItemHeight,int recyclerMeasuredWidth,int recyclerMeasuredHeight){
  int approximateRange;
switch (mLinearLayoutManager.getOrientation()) {
case LinearLayoutManager.HORIZONTAL:
    approximateRange=(int)Math.ceil((float)recyclerMeasuredWidth / (float)firstMeasuredItemWidth);
  break;
default :
approximateRange=(int)Math.ceil((float)recyclerMeasuredHeight / (float)firstMeasuredItemHeight);
break;
}
if (approximateRange < MIN_SANE_RANGE) {
approximateRange=MIN_SANE_RANGE;
}
 else if (approximateRange > MAX_SANE_RANGE) {
approximateRange=MAX_SANE_RANGE;
}
return approximateRange;
}

@Override public int approximateRangeSize(int firstMeasuredItemWidth,int firstMeasuredItemHeight,int recyclerMeasuredWidth,int recyclerMeasuredHeight){
  final int spanCount=mGridLayoutManager.getSpanCount();
switch (mGridLayoutManager.getOrientation()) {
case GridLayoutManager.HORIZONTAL:
    final int colCount=(int)Math.ceil((double)recyclerMeasuredWidth / (double)firstMeasuredItemWidth);
  return colCount * spanCount;
default :
final int rowCount=(int)Math.ceil((double)recyclerMeasuredHeight / (double)firstMeasuredItemHeight);
return rowCount * spanCount;
}
}

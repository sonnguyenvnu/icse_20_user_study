private void cachePreLayoutSpanMapping(){
  final int childCount=getChildCount();
  for (int i=0; i < childCount; i++) {
    final LayoutParams lp=(LayoutParams)getChildAt(i).getLayoutParams();
    final int viewPosition=lp.getViewLayoutPosition();
    mPreLayoutSpanSizeCache.put(viewPosition,lp.getSpanSize());
    mPreLayoutSpanIndexCache.put(viewPosition,lp.getSpanIndex());
  }
}

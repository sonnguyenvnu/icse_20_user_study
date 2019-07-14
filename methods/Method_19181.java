@Override public LayoutInfo getLayoutInfo(ComponentContext c){
  return new StaggeredGridLayoutInfo(mNumSpans,mOrientation,mReverseLayout,mGapStrategy);
}

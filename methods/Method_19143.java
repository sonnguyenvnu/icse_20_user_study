@Override public LayoutInfo getLayoutInfo(ComponentContext c){
  return new GridLayoutInfo(c.getAndroidContext(),mNumColumns,mOrientation,mReverseLayout,mAllowMeasureOverride);
}

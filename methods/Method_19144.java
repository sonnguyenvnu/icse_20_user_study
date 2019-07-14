@Override public LayoutInfo getLayoutInfo(ComponentContext c){
  return mLinearLayoutInfoFactory.createLinearLayoutInfo(c.getAndroidContext(),mOrientation,mReverseLayout);
}

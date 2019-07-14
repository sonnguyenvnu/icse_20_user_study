@Override public void touchExpansionPx(YogaEdge edge,@Px int touchExpansion){
  mPrivateFlags|=PFLAG_TOUCH_EXPANSION_IS_SET;
  Edges mTouchExpansions=(Edges)getOrCreateObjectProps().get(INDEX_TouchExpansions);
  if (mTouchExpansions == null) {
    mTouchExpansions=new Edges();
    getOrCreateObjectProps().append(INDEX_TouchExpansions,mTouchExpansions);
  }
  mTouchExpansions.set(edge,touchExpansion);
}

@Override public int getTouchExpansionLeft(){
  if (!shouldApplyTouchExpansion()) {
    return 0;
  }
  if (YogaConstants.isUndefined(mResolvedTouchExpansionLeft)) {
    mResolvedTouchExpansionLeft=resolveHorizontalEdges(mTouchExpansion,YogaEdge.LEFT);
  }
  return FastMath.round(mResolvedTouchExpansionLeft);
}

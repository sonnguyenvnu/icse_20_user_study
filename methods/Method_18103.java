@Override public int getTouchExpansionRight(){
  if (!shouldApplyTouchExpansion()) {
    return 0;
  }
  if (YogaConstants.isUndefined(mResolvedTouchExpansionRight)) {
    mResolvedTouchExpansionRight=resolveHorizontalEdges(mTouchExpansion,YogaEdge.RIGHT);
  }
  return FastMath.round(mResolvedTouchExpansionRight);
}

private boolean shouldApplyTouchExpansion(){
  return mTouchExpansion != null && mNodeInfo != null && mNodeInfo.hasTouchEventHandlers();
}

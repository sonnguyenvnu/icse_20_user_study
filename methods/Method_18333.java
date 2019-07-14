private void maybeLogInvalidZeroHeight(){
  final ComponentsLogger logger=getComponentContext().getLogger();
  if (logger == null) {
    return;
  }
  if (mComponentTree != null && mComponentTree.getMainThreadLayoutState() != null && mComponentTree.getMainThreadLayoutState().mLayoutRoot == null) {
    return;
  }
  final ComponentLogParams logParams=mInvalidStateLogParams == null ? null : mInvalidStateLogParams.get(ZERO_HEIGHT_LOG);
  if (logParams == null) {
    return;
  }
  final LayoutParams layoutParams=getLayoutParams();
  final boolean isViewBeingRemovedInPreLayoutOfPredictiveAnim=layoutParams instanceof LayoutManagerOverrideParams && ((LayoutManagerOverrideParams)layoutParams).hasValidAdapterPosition();
  if (isViewBeingRemovedInPreLayoutOfPredictiveAnim) {
    return;
  }
  final StringBuilder messageBuilder=new StringBuilder();
  messageBuilder.append(logParams.logProductId);
  messageBuilder.append("-");
  messageBuilder.append(ZERO_HEIGHT_LOG);
  messageBuilder.append(", current=");
  messageBuilder.append((mComponentTree == null ? "null_" + mNullComponentCause : mComponentTree.getSimpleName()));
  messageBuilder.append(", previous=");
  messageBuilder.append(mPreviousComponentSimpleName);
  messageBuilder.append(", view=");
  messageBuilder.append(LithoViewTestHelper.toDebugString(this));
  logError(logger,messageBuilder.toString(),logParams);
}

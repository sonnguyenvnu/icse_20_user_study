public static boolean isEquivalentTo(@Nullable NodeInfo x,@Nullable NodeInfo y){
  if (x == y) {
    return true;
  }
  if (x == null || y == null) {
    return false;
  }
  if (x.getFlags() != y.getFlags()) {
    return false;
  }
  if (!CommonUtils.equals(x.getAccessibilityRole(),y.getAccessibilityRole())) {
    return false;
  }
  if (x.getAlpha() != y.getAlpha()) {
    return false;
  }
  if (!CommonUtils.equals(x.getClickHandler(),y.getClickHandler())) {
    return false;
  }
  if (x.getClipToOutline() != y.getClipToOutline()) {
    return false;
  }
  if (x.getClipChildren() != y.getClipChildren()) {
    return false;
  }
  if (!CommonUtils.equals(x.getContentDescription(),y.getContentDescription())) {
    return false;
  }
  if (!CommonUtils.equals(x.getDispatchPopulateAccessibilityEventHandler(),y.getDispatchPopulateAccessibilityEventHandler())) {
    return false;
  }
  if (x.getEnabledState() != y.getEnabledState()) {
    return false;
  }
  if (!CommonUtils.equals(x.getFocusChangeHandler(),y.getFocusChangeHandler())) {
    return false;
  }
  if (x.getFocusState() != y.getFocusState()) {
    return false;
  }
  if (!CommonUtils.equals(x.getInterceptTouchHandler(),y.getInterceptTouchHandler())) {
    return false;
  }
  if (!CommonUtils.equals(x.getLongClickHandler(),y.getLongClickHandler())) {
    return false;
  }
  if (!CommonUtils.equals(x.getOnInitializeAccessibilityEventHandler(),y.getOnInitializeAccessibilityEventHandler())) {
    return false;
  }
  if (!CommonUtils.equals(x.getOnInitializeAccessibilityNodeInfoHandler(),y.getOnInitializeAccessibilityNodeInfoHandler())) {
    return false;
  }
  if (!CommonUtils.equals(x.getOnPopulateAccessibilityEventHandler(),y.getOnPopulateAccessibilityEventHandler())) {
    return false;
  }
  if (!CommonUtils.equals(x.getOnRequestSendAccessibilityEventHandler(),y.getOnRequestSendAccessibilityEventHandler())) {
    return false;
  }
  if (!CommonUtils.equals(x.getOutlineProvider(),y.getOutlineProvider())) {
    return false;
  }
  if (!CommonUtils.equals(x.getPerformAccessibilityActionHandler(),y.getPerformAccessibilityActionHandler())) {
    return false;
  }
  if (x.getRotation() != y.getRotation()) {
    return false;
  }
  if (x.getScale() != y.getScale()) {
    return false;
  }
  if (x.getSelectedState() != y.getSelectedState()) {
    return false;
  }
  if (!CommonUtils.equals(x.getSendAccessibilityEventHandler(),y.getSendAccessibilityEventHandler())) {
    return false;
  }
  if (!CommonUtils.equals(x.getSendAccessibilityEventUncheckedHandler(),y.getSendAccessibilityEventUncheckedHandler())) {
    return false;
  }
  if (x.getShadowElevation() != y.getShadowElevation()) {
    return false;
  }
  if (!CommonUtils.equals(x.getTouchHandler(),y.getTouchHandler())) {
    return false;
  }
  if (!CommonUtils.equals(x.getViewTag(),y.getViewTag())) {
    return false;
  }
  if (!CommonUtils.equals(x.getViewTags(),y.getViewTags())) {
    return false;
  }
  return true;
}

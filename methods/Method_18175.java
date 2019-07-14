@Override public void copyInto(NodeInfo target){
  if ((mPrivateFlags & PFLAG_CLICK_HANDLER_IS_SET) != 0) {
    target.setClickHandler(mClickHandler);
  }
  if ((mPrivateFlags & PFLAG_LONG_CLICK_HANDLER_IS_SET) != 0) {
    target.setLongClickHandler(mLongClickHandler);
  }
  if ((mPrivateFlags & PFLAG_FOCUS_CHANGE_HANDLER_IS_SET) != 0) {
    target.setFocusChangeHandler(mFocusChangeHandler);
  }
  if ((mPrivateFlags & PFLAG_TOUCH_HANDLER_IS_SET) != 0) {
    target.setTouchHandler(mTouchHandler);
  }
  if ((mPrivateFlags & PFLAG_INTERCEPT_TOUCH_HANDLER_IS_SET) != 0) {
    target.setInterceptTouchHandler(mInterceptTouchHandler);
  }
  if ((mPrivateFlags & PFLAG_ACCESSIBILITY_ROLE_IS_SET) != 0) {
    target.setAccessibilityRole(mAccessibilityRole);
  }
  if ((mPrivateFlags & PFLAG_ACCESSIBILITY_ROLE_DESCRIPTION_IS_SET) != 0) {
    target.setAccessibilityRoleDescription(mAccessibilityRoleDescription);
  }
  if ((mPrivateFlags & PFLAG_DISPATCH_POPULATE_ACCESSIBILITY_EVENT_HANDLER_IS_SET) != 0) {
    target.setDispatchPopulateAccessibilityEventHandler(mDispatchPopulateAccessibilityEventHandler);
  }
  if ((mPrivateFlags & PFLAG_ON_INITIALIZE_ACCESSIBILITY_EVENT_HANDLER_IS_SET) != 0) {
    target.setOnInitializeAccessibilityEventHandler(mOnInitializeAccessibilityEventHandler);
  }
  if ((mPrivateFlags & PFLAG_ON_INITIALIZE_ACCESSIBILITY_NODE_INFO_HANDLER_IS_SET) != 0) {
    target.setOnInitializeAccessibilityNodeInfoHandler(mOnInitializeAccessibilityNodeInfoHandler);
  }
  if ((mPrivateFlags & PFLAG_ON_POPULATE_ACCESSIBILITY_EVENT_HANDLER_IS_SET) != 0) {
    target.setOnPopulateAccessibilityEventHandler(mOnPopulateAccessibilityEventHandler);
  }
  if ((mPrivateFlags & PFLAG_ON_REQUEST_SEND_ACCESSIBILITY_EVENT_HANDLER_IS_SET) != 0) {
    target.setOnRequestSendAccessibilityEventHandler(mOnRequestSendAccessibilityEventHandler);
  }
  if ((mPrivateFlags & PFLAG_PERFORM_ACCESSIBILITY_ACTION_HANDLER_IS_SET) != 0) {
    target.setPerformAccessibilityActionHandler(mPerformAccessibilityActionHandler);
  }
  if ((mPrivateFlags & PFLAG_SEND_ACCESSIBILITY_EVENT_HANDLER_IS_SET) != 0) {
    target.setSendAccessibilityEventHandler(mSendAccessibilityEventHandler);
  }
  if ((mPrivateFlags & PFLAG_SEND_ACCESSIBILITY_EVENT_UNCHECKED_HANDLER_IS_SET) != 0) {
    target.setSendAccessibilityEventUncheckedHandler(mSendAccessibilityEventUncheckedHandler);
  }
  if ((mPrivateFlags & PFLAG_CONTENT_DESCRIPTION_IS_SET) != 0) {
    target.setContentDescription(mContentDescription);
  }
  if ((mPrivateFlags & PFLAG_SHADOW_ELEVATION_IS_SET) != 0) {
    target.setShadowElevation(mShadowElevation);
  }
  if ((mPrivateFlags & PFLAG_OUTINE_PROVIDER_IS_SET) != 0) {
    target.setOutlineProvider(mOutlineProvider);
  }
  if ((mPrivateFlags & PFLAG_CLIP_TO_OUTLINE_IS_SET) != 0) {
    target.setClipToOutline(mClipToOutline);
  }
  if ((mPrivateFlags & PFLAG_CLIP_CHILDREN_IS_SET) != 0) {
    target.setClipChildren(mClipChildren);
  }
  if (mViewTag != null) {
    target.setViewTag(mViewTag);
  }
  if (mViewTags != null) {
    target.setViewTags(mViewTags);
  }
  if (getFocusState() != FOCUS_UNSET) {
    target.setFocusable(getFocusState() == FOCUS_SET_TRUE);
  }
  if (getClickableState() != CLICKABLE_UNSET) {
    target.setClickable(getClickableState() == CLICKABLE_SET_TRUE);
  }
  if (getEnabledState() != ENABLED_UNSET) {
    target.setEnabled(getEnabledState() == ENABLED_SET_TRUE);
  }
  if (getSelectedState() != SELECTED_UNSET) {
    target.setSelected(getSelectedState() == SELECTED_SET_TRUE);
  }
  if ((mPrivateFlags & PFLAG_SCALE_IS_SET) != 0) {
    target.setScale(mScale);
  }
  if ((mPrivateFlags & PFLAG_ALPHA_IS_SET) != 0) {
    target.setAlpha(mAlpha);
  }
  if ((mPrivateFlags & PFLAG_ROTATION_IS_SET) != 0) {
    target.setRotation(mRotation);
  }
  if ((mPrivateFlags & PFLAG_ROTATION_X_IS_SET) != 0) {
    target.setRotationX(mRotationX);
  }
  if ((mPrivateFlags & PFLAG_ROTATION_Y_IS_SET) != 0) {
    target.setRotationY(mRotationY);
  }
}

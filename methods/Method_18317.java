/** 
 * Call this to tell the LithoView whether it is visible or not. In general, you shouldn't require this as the system will do this for you. However, when a new activity/fragment is added on top of the one hosting this view, the LithoView remains in the backstack but receives no callback to indicate that it is no longer visible.
 * @param isVisible if true, this will find the current visible rect and process visibilityoutputs using it. If false, any invisible and unfocused events will be called.
 */
public void setVisibilityHint(boolean isVisible){
  assertMainThread();
  if (mComponentTree == null || !mComponentTree.isIncrementalMountEnabled()) {
    return;
  }
  if (isVisible) {
    if (getLocalVisibleRect(new Rect())) {
      mComponentTree.processVisibilityOutputs();
      recursivelySetVisibleHint(true);
    }
  }
 else {
    recursivelySetVisibleHint(false);
    mMountState.clearVisibilityItems();
  }
}

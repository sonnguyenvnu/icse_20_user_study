private void updateViews(boolean animate){
  boolean authenticating=mAuthenticateRequest.isRequesting();
  mFormLayout.setEnabled(!authenticating);
  if (animate) {
    if (authenticating) {
      ViewUtils.fadeOutThenFadeIn(mFormLayout,mProgress,false);
    }
 else {
      ViewUtils.fadeOutThenFadeIn(mProgress,mFormLayout,false);
    }
  }
 else {
    ViewUtils.setVisibleOrInvisible(mFormLayout,!authenticating);
    ViewUtils.setVisibleOrInvisible(mProgress,authenticating);
  }
}

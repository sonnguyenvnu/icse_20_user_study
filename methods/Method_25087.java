private void hideMenuButtonWithImage(boolean animate){
  if (!isMenuButtonHidden()) {
    mMenuButton.hide(animate);
    if (animate) {
      mImageToggle.startAnimation(mImageToggleHideAnimation);
    }
    mImageToggle.setVisibility(INVISIBLE);
    mIsMenuButtonAnimationRunning=false;
  }
}

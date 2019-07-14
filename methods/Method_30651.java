@Override public void onUserChanged(int requestCode,User newUser){
  if (!mScrollLayout.isHeaderOpen()) {
    for (    ViewGroup animateChangesLayout : mAnimateChangesLayouts) {
      animateChangesLayout.setLayoutTransition(null);
    }
  }
  mHeaderLayout.bindUser(newUser);
  updateOptionsMenu();
}

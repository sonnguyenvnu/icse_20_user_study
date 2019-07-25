public void hide(boolean animate){
  if (animate) {
    UiUtils.fadeOutAnim(this);
  }
 else {
    setVisibility(GONE);
  }
}

public void show(boolean animate){
  if (animate) {
    UiUtils.fadeInAnim(this);
  }
 else {
    setVisibility(VISIBLE);
  }
}

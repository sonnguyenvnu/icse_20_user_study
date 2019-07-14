private void setViewVisible(View view,boolean visible,boolean animate){
  if (view == null) {
    if (visible) {
      throw new IllegalStateException("Missing view when setting to visible");
    }
 else {
      return;
    }
  }
  if (animate) {
    ViewUtils.fadeToVisibility(view,visible);
  }
 else {
    ViewUtils.setVisibleOrGone(view,visible);
  }
}

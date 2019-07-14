protected void onChildPressed(View child,boolean pressed){
  if (disableHighlightState) {
    return;
  }
  child.setPressed(pressed);
}

public void disable(){
  View view=getWrapper();
  view.setFocusable(false);
  view.setFocusableInTouchMode(false);
  makeUnfocused();
}

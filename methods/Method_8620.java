@Override protected boolean canDismissWithTouchOutside(){
  return fullscreenVideoContainer.getVisibility() != View.VISIBLE;
}

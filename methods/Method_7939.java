private void resetPressedLink(int type){
  if (pressedLink == null || pressedLinkType != type && type != -1) {
    return;
  }
  resetUrlPaths(false);
  pressedLink=null;
  pressedLinkType=-1;
  invalidate();
}

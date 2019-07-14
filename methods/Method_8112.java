protected void resetPressedLink(){
  pressedLink=-1;
  linkPreviewPressed=false;
  cancelCheckLongPress();
  invalidate();
}

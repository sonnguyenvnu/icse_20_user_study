private void resetPressedLink(){
  if (pressedLink != null) {
    pressedLink=null;
  }
  invalidate();
}

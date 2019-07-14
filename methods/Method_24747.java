@Override public void handleRun(int modifiers){
  boolean shift=(modifiers & InputEvent.SHIFT_MASK) != 0;
  if (shift) {
    jeditor.handlePresent();
  }
 else {
    jeditor.handleRun();
  }
}

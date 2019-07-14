/** 
 * Perform a scroll, either from dragging the screen or by scrolling a mouse wheel. 
 */
void doScroll(MotionEvent event,int rowsDown){
  boolean up=rowsDown < 0;
  int amount=Math.abs(rowsDown);
  for (int i=0; i < amount; i++) {
    if (mEmulator.isMouseTrackingActive()) {
      sendMouseEventCode(event,up ? TerminalEmulator.MOUSE_WHEELUP_BUTTON : TerminalEmulator.MOUSE_WHEELDOWN_BUTTON,true);
    }
 else     if (mEmulator.isAlternateBufferActive()) {
      handleKeyCode(up ? KeyEvent.KEYCODE_DPAD_UP : KeyEvent.KEYCODE_DPAD_DOWN,0);
    }
 else {
      mTopRow=Math.min(0,Math.max(-(mEmulator.getScreen().getActiveTranscriptRows()),mTopRow + (up ? -1 : 1)));
      if (!awakenScrollBars())       invalidate();
    }
  }
}

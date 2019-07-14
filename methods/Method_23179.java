/** 
 * Set the window (and dock, or whatever necessary) title. 
 */
@Override public void setTitle(String title){
  frame.setTitle(title);
  if (cursorVisible && (PApplet.platform == PConstants.MACOSX) && (cursorType != PConstants.ARROW)) {
    hideCursor();
    showCursor();
  }
}

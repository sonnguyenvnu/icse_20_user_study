@Override public void showCursor(){
  if (!cursorVisible) {
    cursorVisible=true;
    canvas.setCursor(Cursor.getPredefinedCursor(cursorType));
  }
}

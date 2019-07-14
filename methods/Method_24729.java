public boolean handleTyped(KeyEvent event){
  char c=event.getKeyChar();
  if ((event.getModifiers() & InputEvent.CTRL_MASK) != 0) {
    if (c == KeyEvent.VK_COMMA) {
      event.consume();
      return true;
    }
    if (c == KeyEvent.VK_SPACE) {
      event.consume();
      return true;
    }
  }
  return false;
}

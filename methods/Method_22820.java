protected boolean isMnemonic(KeyEvent event){
  if (!Platform.isMacOS()) {
    if ((event.getModifiers() & InputEvent.ALT_MASK) != 0 && (event.getModifiers() & InputEvent.CTRL_MASK) == 0 && event.getKeyChar() != KeyEvent.VK_UNDEFINED) {
      return true;
    }
  }
  return false;
}

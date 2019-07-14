/** 
 * Handle a key typed event. This inserts the key into the text area.
 */
public void keyTyped(KeyEvent evt){
  int modifiers=evt.getModifiers();
  char c=evt.getKeyChar();
  if ((modifiers & InputEvent.META_MASK) != 0)   return;
  if ((modifiers & InputEvent.CTRL_MASK) != 0 && c == '/')   return;
  if (c != KeyEvent.CHAR_UNDEFINED) {
    if (c >= 0x20 && c != 0x7f) {
      KeyStroke keyStroke=KeyStroke.getKeyStroke(Character.toUpperCase(c));
      Object o=currentBindings.get(keyStroke);
      if (o instanceof Map) {
        currentBindings=(Map)o;
        return;
      }
 else       if (o instanceof ActionListener) {
        currentBindings=bindings;
        executeAction((ActionListener)o,evt.getSource(),String.valueOf(c));
        return;
      }
      currentBindings=bindings;
      if (grabAction != null) {
        handleGrabAction(evt);
        return;
      }
      if (repeat && Character.isDigit(c)) {
        repeatCount*=10;
        repeatCount+=(c - '0');
        return;
      }
      executeAction(INSERT_CHAR,evt.getSource(),String.valueOf(evt.getKeyChar()));
      repeatCount=0;
      repeat=false;
    }
  }
}

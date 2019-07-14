protected void fxKeyEvent(javafx.scene.input.KeyEvent fxEvent){
  int action=0;
  EventType<? extends KeyEvent> et=fxEvent.getEventType();
  if (et == KeyEvent.KEY_PRESSED) {
    action=processing.event.KeyEvent.PRESS;
  }
 else   if (et == KeyEvent.KEY_RELEASED) {
    action=processing.event.KeyEvent.RELEASE;
  }
 else   if (et == KeyEvent.KEY_TYPED) {
    action=processing.event.KeyEvent.TYPE;
  }
  int modifiers=0;
  if (fxEvent.isShiftDown()) {
    modifiers|=processing.event.Event.SHIFT;
  }
  if (fxEvent.isControlDown()) {
    modifiers|=processing.event.Event.CTRL;
  }
  if (fxEvent.isMetaDown()) {
    modifiers|=processing.event.Event.META;
  }
  if (fxEvent.isAltDown()) {
    modifiers|=processing.event.Event.ALT;
  }
  long when=System.currentTimeMillis();
  char keyChar=getKeyChar(fxEvent);
  int keyCode=getKeyCode(fxEvent);
  sketch.postEvent(new processing.event.KeyEvent(fxEvent,when,action,modifiers,keyChar,keyCode));
}

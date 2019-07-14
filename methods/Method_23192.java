protected void nativeKeyEvent(java.awt.event.KeyEvent event){
  int peAction=0;
switch (event.getID()) {
case java.awt.event.KeyEvent.KEY_PRESSED:
    peAction=KeyEvent.PRESS;
  break;
case java.awt.event.KeyEvent.KEY_RELEASED:
peAction=KeyEvent.RELEASE;
break;
case java.awt.event.KeyEvent.KEY_TYPED:
peAction=KeyEvent.TYPE;
break;
}
int peModifiers=event.getModifiers() & (InputEvent.SHIFT_MASK | InputEvent.CTRL_MASK | InputEvent.META_MASK | InputEvent.ALT_MASK);
sketch.postEvent(new KeyEvent(event,event.getWhen(),peAction,peModifiers,event.getKeyChar(),event.getKeyCode()));
}

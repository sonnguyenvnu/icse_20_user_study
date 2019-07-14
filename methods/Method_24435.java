protected void nativeKeyEvent(com.jogamp.newt.event.KeyEvent nativeEvent,int peAction){
  int peModifiers=nativeEvent.getModifiers() & (InputEvent.SHIFT_MASK | InputEvent.CTRL_MASK | InputEvent.META_MASK | InputEvent.ALT_MASK);
  short code=nativeEvent.getKeyCode();
  char keyChar;
  int keyCode;
  if (isPCodedKey(code)) {
    keyCode=mapToPConst(code);
    keyChar=PConstants.CODED;
  }
 else   if (isHackyKey(code)) {
    keyCode=code == com.jogamp.newt.event.KeyEvent.VK_ENTER ? PConstants.ENTER : code;
    keyChar=hackToChar(code,nativeEvent.getKeyChar());
  }
 else {
    keyCode=code;
    keyChar=nativeEvent.getKeyChar();
  }
  KeyEvent ke=new KeyEvent(nativeEvent,nativeEvent.getWhen(),peAction,peModifiers,keyChar,keyCode,nativeEvent.isAutoRepeat());
  sketch.postEvent(ke);
  if (!isPCodedKey(code) && !isHackyKey(code)) {
    if (peAction == KeyEvent.PRESS) {
      KeyEvent tke=new KeyEvent(nativeEvent,nativeEvent.getWhen(),KeyEvent.TYPE,peModifiers,keyChar,0,nativeEvent.isAutoRepeat());
      sketch.postEvent(tke);
    }
  }
}

/** 
 * Figure out how to process a mouse event. When loop() has been called, the events will be queued up until drawing is complete. If noLoop() has been called, then events will happen immediately.
 */
protected void nativeMouseEvent(java.awt.event.MouseEvent nativeEvent){
  int peCount=nativeEvent.getClickCount();
  int peAction=0;
switch (nativeEvent.getID()) {
case java.awt.event.MouseEvent.MOUSE_PRESSED:
    peAction=MouseEvent.PRESS;
  break;
case java.awt.event.MouseEvent.MOUSE_RELEASED:
peAction=MouseEvent.RELEASE;
break;
case java.awt.event.MouseEvent.MOUSE_CLICKED:
peAction=MouseEvent.CLICK;
break;
case java.awt.event.MouseEvent.MOUSE_DRAGGED:
peAction=MouseEvent.DRAG;
break;
case java.awt.event.MouseEvent.MOUSE_MOVED:
peAction=MouseEvent.MOVE;
break;
case java.awt.event.MouseEvent.MOUSE_ENTERED:
peAction=MouseEvent.ENTER;
break;
case java.awt.event.MouseEvent.MOUSE_EXITED:
peAction=MouseEvent.EXIT;
break;
case java.awt.event.MouseEvent.MOUSE_WHEEL:
peAction=MouseEvent.WHEEL;
peCount=((MouseWheelEvent)nativeEvent).getWheelRotation();
break;
}
int modifiers=nativeEvent.getModifiers();
int peModifiers=modifiers & (InputEvent.SHIFT_MASK | InputEvent.CTRL_MASK | InputEvent.META_MASK | InputEvent.ALT_MASK);
int peButton=0;
if ((modifiers & InputEvent.BUTTON1_MASK) != 0) {
peButton=PConstants.LEFT;
}
 else if ((modifiers & InputEvent.BUTTON2_MASK) != 0) {
peButton=PConstants.CENTER;
}
 else if ((modifiers & InputEvent.BUTTON3_MASK) != 0) {
peButton=PConstants.RIGHT;
}
sketch.postEvent(new MouseEvent(nativeEvent,nativeEvent.getWhen(),peAction,peModifiers,nativeEvent.getX() / windowScaleFactor,nativeEvent.getY() / windowScaleFactor,peButton,peCount));
}

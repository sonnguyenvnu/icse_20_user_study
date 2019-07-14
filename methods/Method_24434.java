protected void nativeMouseEvent(com.jogamp.newt.event.MouseEvent nativeEvent,int peAction){
  int modifiers=nativeEvent.getModifiers();
  int peModifiers=modifiers & (InputEvent.SHIFT_MASK | InputEvent.CTRL_MASK | InputEvent.META_MASK | InputEvent.ALT_MASK);
  int peButton=0;
switch (nativeEvent.getButton()) {
case com.jogamp.newt.event.MouseEvent.BUTTON1:
    peButton=PConstants.LEFT;
  break;
case com.jogamp.newt.event.MouseEvent.BUTTON2:
peButton=PConstants.CENTER;
break;
case com.jogamp.newt.event.MouseEvent.BUTTON3:
peButton=PConstants.RIGHT;
break;
}
int peCount=0;
if (peAction == MouseEvent.WHEEL) {
peCount=-(nativeEvent.isShiftDown() ? (int)nativeEvent.getRotation()[0] : (int)nativeEvent.getRotation()[1]);
}
 else {
peCount=nativeEvent.getClickCount();
}
int scale;
if (PApplet.platform == PConstants.MACOSX) {
scale=(int)getCurrentPixelScale();
}
 else {
scale=(int)getPixelScale();
}
int sx=nativeEvent.getX() / scale;
int sy=nativeEvent.getY() / scale;
int mx=sx;
int my=sy;
if (pgl.presentMode()) {
mx-=(int)pgl.presentX;
my-=(int)pgl.presentY;
if (peAction == KeyEvent.RELEASE && pgl.insideStopButton(sx,sy - screenRect.height / windowScaleFactor)) {
sketch.exit();
}
if (mx < 0 || sketchWidth < mx || my < 0 || sketchHeight < my) {
return;
}
}
MouseEvent me=new MouseEvent(nativeEvent,nativeEvent.getWhen(),peAction,peModifiers,mx,my,peButton,peCount);
sketch.postEvent(me);
}

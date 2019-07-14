private static int mapToPConst(short code){
switch (code) {
case com.jogamp.newt.event.KeyEvent.VK_UP:
    return PConstants.UP;
case com.jogamp.newt.event.KeyEvent.VK_DOWN:
  return PConstants.DOWN;
case com.jogamp.newt.event.KeyEvent.VK_LEFT:
return PConstants.LEFT;
case com.jogamp.newt.event.KeyEvent.VK_RIGHT:
return PConstants.RIGHT;
case com.jogamp.newt.event.KeyEvent.VK_ALT:
return PConstants.ALT;
case com.jogamp.newt.event.KeyEvent.VK_CONTROL:
return PConstants.CONTROL;
case com.jogamp.newt.event.KeyEvent.VK_SHIFT:
return PConstants.SHIFT;
case com.jogamp.newt.event.KeyEvent.VK_WINDOWS:
return java.awt.event.KeyEvent.VK_META;
default :
return code;
}
}

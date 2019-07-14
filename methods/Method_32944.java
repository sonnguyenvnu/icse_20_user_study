/** 
 * Send a single mouse event code to the terminal. 
 */
void sendMouseEventCode(MotionEvent e,int button,boolean pressed){
  int x=(int)(e.getX() / mRenderer.mFontWidth) + 1;
  int y=(int)((e.getY() - mRenderer.mFontLineSpacingAndAscent) / mRenderer.mFontLineSpacing) + 1;
  if (pressed && (button == TerminalEmulator.MOUSE_WHEELDOWN_BUTTON || button == TerminalEmulator.MOUSE_WHEELUP_BUTTON)) {
    if (mMouseStartDownTime == e.getDownTime()) {
      x=mMouseScrollStartX;
      y=mMouseScrollStartY;
    }
 else {
      mMouseStartDownTime=e.getDownTime();
      mMouseScrollStartX=x;
      mMouseScrollStartY=y;
    }
  }
  mEmulator.sendMouseEvent(button,x,y,pressed);
}

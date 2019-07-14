protected void fxScrollEvent(ScrollEvent fxEvent){
  int count=(int)-(fxEvent.getDeltaY() / fxEvent.getMultiplierY());
  int action=processing.event.MouseEvent.WHEEL;
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
  int button=0;
  long when=System.currentTimeMillis();
  int x=(int)fxEvent.getX();
  int y=(int)fxEvent.getY();
  sketch.postEvent(new processing.event.MouseEvent(fxEvent,when,action,modifiers,x,y,button,count));
}

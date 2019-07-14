protected void fxMouseEvent(MouseEvent fxEvent){
  int count=fxEvent.getClickCount();
  int action=mouseMap.get(fxEvent.getEventType());
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
switch (fxEvent.getButton()) {
case PRIMARY:
    button=PConstants.LEFT;
  break;
case SECONDARY:
button=PConstants.RIGHT;
break;
case MIDDLE:
button=PConstants.CENTER;
break;
case NONE:
break;
}
long when=System.currentTimeMillis();
int x=(int)fxEvent.getX();
int y=(int)fxEvent.getY();
sketch.postEvent(new processing.event.MouseEvent(fxEvent,when,action,modifiers,x,y,button,count));
}

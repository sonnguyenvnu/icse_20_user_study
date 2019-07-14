/** 
 * Actually take action based on a mouse event. Internally updates mouseX, mouseY, mousePressed, and mouseEvent. Then it calls the event type with no params, i.e. mousePressed() or mouseReleased() that the user may have overloaded to do something more useful.
 */
protected void handleMouseEvent(MouseEvent event){
  final int action=event.getAction();
  if (action == MouseEvent.DRAG || action == MouseEvent.MOVE || action == MouseEvent.PRESS) {
    pmouseX=emouseX;
    pmouseY=emouseY;
    mouseX=event.getX();
    mouseY=event.getY();
  }
  int button=event.getButton();
  if (PApplet.platform == PConstants.MACOSX && event.getButton() == PConstants.LEFT) {
    if (action == MouseEvent.PRESS && event.isControlDown()) {
      macosxLeftButtonWithCtrlPressed=true;
    }
    if (macosxLeftButtonWithCtrlPressed) {
      button=PConstants.RIGHT;
      event=new MouseEvent(event.getNative(),event.getMillis(),event.getAction(),event.getModifiers(),event.getX(),event.getY(),button,event.getCount());
    }
    if (action == MouseEvent.RELEASE) {
      macosxLeftButtonWithCtrlPressed=false;
    }
  }
  mouseButton=button;
  if (firstMouse) {
    pmouseX=mouseX;
    pmouseY=mouseY;
    dmouseX=mouseX;
    dmouseY=mouseY;
    firstMouse=false;
  }
  mouseEvent=event;
switch (action) {
case MouseEvent.PRESS:
    mousePressed=true;
  break;
case MouseEvent.RELEASE:
mousePressed=false;
break;
}
handleMethods("mouseEvent",new Object[]{event});
switch (action) {
case MouseEvent.PRESS:
mousePressed(event);
break;
case MouseEvent.RELEASE:
mouseReleased(event);
break;
case MouseEvent.CLICK:
mouseClicked(event);
break;
case MouseEvent.DRAG:
mouseDragged(event);
break;
case MouseEvent.MOVE:
mouseMoved(event);
break;
case MouseEvent.ENTER:
mouseEntered(event);
break;
case MouseEvent.EXIT:
mouseExited(event);
break;
case MouseEvent.WHEEL:
mouseWheel(event);
break;
}
if ((action == MouseEvent.DRAG) || (action == MouseEvent.MOVE)) {
emouseX=mouseX;
emouseY=mouseY;
}
}

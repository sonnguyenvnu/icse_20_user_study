/** 
 * Register a built-in event so that it can be fired for libraries, etc. Supported events include: <ul> <li>pre – at the very top of the draw() method (safe to draw) <li>draw – at the end of the draw() method (safe to draw) <li>post – after draw() has exited (not safe to draw) <li>pause – called when the sketch is paused <li>resume – called when the sketch is resumed <li>dispose – when the sketch is shutting down (definitely not safe to draw) <ul> In addition, the new (for 2.0) processing.event classes are passed to the following event types: <ul> <li>mouseEvent <li>keyEvent <li>touchEvent </ul> The older java.awt events are no longer supported. See the Library Wiki page for more details.
 * @param methodName name of the method to be called
 * @param target the target object that should receive the event
 */
public void registerMethod(String methodName,Object target){
  if (methodName.equals("mouseEvent")) {
    registerWithArgs("mouseEvent",target,new Class[]{processing.event.MouseEvent.class});
  }
 else   if (methodName.equals("keyEvent")) {
    registerWithArgs("keyEvent",target,new Class[]{processing.event.KeyEvent.class});
  }
 else   if (methodName.equals("touchEvent")) {
    registerWithArgs("touchEvent",target,new Class[]{processing.event.TouchEvent.class});
  }
 else {
    registerNoArgs(methodName,target);
  }
}

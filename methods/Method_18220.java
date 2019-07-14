static boolean dispatchOnTouch(EventHandler<TouchEvent> touchHandler,View view,MotionEvent event){
  assertMainThread();
  if (sTouchEvent == null) {
    sTouchEvent=new TouchEvent();
  }
  sTouchEvent.view=view;
  sTouchEvent.motionEvent=event;
  final EventDispatcher eventDispatcher=touchHandler.mHasEventDispatcher.getEventDispatcher();
  final Object returnValue=eventDispatcher.dispatchOnEvent(touchHandler,sTouchEvent);
  sTouchEvent.view=null;
  sTouchEvent.motionEvent=null;
  return returnValue != null && (boolean)returnValue;
}

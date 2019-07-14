static boolean dispatchOnInterceptTouch(EventHandler<InterceptTouchEvent> interceptTouchHandler,View view,MotionEvent event){
  assertMainThread();
  if (sInterceptTouchEvent == null) {
    sInterceptTouchEvent=new InterceptTouchEvent();
  }
  sInterceptTouchEvent.motionEvent=event;
  sInterceptTouchEvent.view=view;
  final EventDispatcher eventDispatcher=interceptTouchHandler.mHasEventDispatcher.getEventDispatcher();
  final Object returnValue=eventDispatcher.dispatchOnEvent(interceptTouchHandler,sInterceptTouchEvent);
  sInterceptTouchEvent.motionEvent=null;
  sInterceptTouchEvent.view=null;
  return returnValue != null && (boolean)returnValue;
}

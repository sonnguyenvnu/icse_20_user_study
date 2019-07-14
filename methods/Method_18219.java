static boolean dispatchOnLongClick(EventHandler<LongClickEvent> longClickHandler,View view){
  assertMainThread();
  if (sLongClickEvent == null) {
    sLongClickEvent=new LongClickEvent();
  }
  sLongClickEvent.view=view;
  final EventDispatcher eventDispatcher=longClickHandler.mHasEventDispatcher.getEventDispatcher();
  final Object returnValue=eventDispatcher.dispatchOnEvent(longClickHandler,sLongClickEvent);
  sLongClickEvent.view=null;
  return returnValue != null && (boolean)returnValue;
}

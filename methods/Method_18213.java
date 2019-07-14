static void dispatchOnFocusChanged(EventHandler<FocusChangedEvent> focusChangeHandler,View view,boolean hasFocus){
  assertMainThread();
  if (sFocusChangedEvent == null) {
    sFocusChangedEvent=new FocusChangedEvent();
  }
  sFocusChangedEvent.view=view;
  sFocusChangedEvent.hasFocus=hasFocus;
  final EventDispatcher eventDispatcher=focusChangeHandler.mHasEventDispatcher.getEventDispatcher();
  eventDispatcher.dispatchOnEvent(focusChangeHandler,sFocusChangedEvent);
  sFocusChangedEvent.view=null;
}

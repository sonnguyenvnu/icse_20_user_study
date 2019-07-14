static void dispatchOnClick(EventHandler<ClickEvent> clickHandler,View view){
  assertMainThread();
  if (sClickEvent == null) {
    sClickEvent=new ClickEvent();
  }
  sClickEvent.view=view;
  final EventDispatcher eventDispatcher=clickHandler.mHasEventDispatcher.getEventDispatcher();
  eventDispatcher.dispatchOnEvent(clickHandler,sClickEvent);
  sClickEvent.view=null;
}

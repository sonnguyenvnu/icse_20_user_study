static void dispatchOnVisibilityChanged(EventHandler<VisibilityChangedEvent> visibilityChangedHandler,int visibleWidth,int visibleHeight,float percentVisibleWidth,float percentVisibleHeight){
  assertMainThread();
  if (sVisibleRectChangedEvent == null) {
    sVisibleRectChangedEvent=new VisibilityChangedEvent();
  }
  sVisibleRectChangedEvent.visibleHeight=visibleHeight;
  sVisibleRectChangedEvent.visibleWidth=visibleWidth;
  sVisibleRectChangedEvent.percentVisibleHeight=percentVisibleHeight;
  sVisibleRectChangedEvent.percentVisibleWidth=percentVisibleWidth;
  visibilityChangedHandler.dispatchEvent(sVisibleRectChangedEvent);
}

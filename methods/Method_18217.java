static void dispatchOnInvisible(EventHandler<InvisibleEvent> invisibleHandler){
  assertMainThread();
  if (sInvisibleEvent == null) {
    sInvisibleEvent=new InvisibleEvent();
  }
  invisibleHandler.dispatchEvent(sInvisibleEvent);
}

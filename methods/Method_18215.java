static void dispatchOnUnfocused(EventHandler<UnfocusedVisibleEvent> unfocusedHandler){
  assertMainThread();
  if (sUnfocusedVisibleEvent == null) {
    sUnfocusedVisibleEvent=new UnfocusedVisibleEvent();
  }
  unfocusedHandler.dispatchEvent(sUnfocusedVisibleEvent);
}

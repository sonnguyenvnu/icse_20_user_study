static void dispatchOnFocused(EventHandler<FocusedVisibleEvent> focusedHandler){
  assertMainThread();
  if (sFocusedVisibleEvent == null) {
    sFocusedVisibleEvent=new FocusedVisibleEvent();
  }
  focusedHandler.dispatchEvent(sFocusedVisibleEvent);
}

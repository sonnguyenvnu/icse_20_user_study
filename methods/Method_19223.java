@OnTrigger(ClearFocusEvent.class) static void clearFocus(ComponentContext c,@State AtomicReference<EditTextWithEventHandlers> mountedView){
  EditTextWithEventHandlers eventHandler=mountedView.get();
  if (eventHandler != null) {
    eventHandler.clearFocus();
    InputMethodManager imm=(InputMethodManager)c.getAndroidContext().getSystemService(Context.INPUT_METHOD_SERVICE);
    imm.hideSoftInputFromWindow(eventHandler.getWindowToken(),0);
  }
}

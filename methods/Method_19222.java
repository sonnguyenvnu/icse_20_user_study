@OnTrigger(RequestFocusEvent.class) static void requestFocus(ComponentContext c,@State AtomicReference<EditTextWithEventHandlers> mountedView){
  EditTextWithEventHandlers eventHandler=mountedView.get();
  if (eventHandler != null) {
    if (eventHandler.requestFocus()) {
      InputMethodManager imm=(InputMethodManager)c.getAndroidContext().getSystemService(Context.INPUT_METHOD_SERVICE);
      imm.showSoftInput(eventHandler,0);
    }
  }
}

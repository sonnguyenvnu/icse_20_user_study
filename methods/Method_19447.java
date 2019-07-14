@OnTrigger(RequestFocusEvent.class) static void requestFocus(ComponentContext c,@State AtomicReference<EditTextWithEventHandlers> mountedView){
  EditTextWithEventHandlers view=mountedView.get();
  if (view != null) {
    if (view.requestFocus()) {
      InputMethodManager imm=(InputMethodManager)c.getAndroidContext().getSystemService(Context.INPUT_METHOD_SERVICE);
      imm.showSoftInput(view,0);
    }
  }
}

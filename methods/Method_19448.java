@OnTrigger(ClearFocusEvent.class) static void clearFocus(ComponentContext c,@State AtomicReference<EditTextWithEventHandlers> mountedView){
  EditTextWithEventHandlers view=mountedView.get();
  if (view != null) {
    view.clearFocus();
    InputMethodManager imm=(InputMethodManager)c.getAndroidContext().getSystemService(Context.INPUT_METHOD_SERVICE);
    imm.hideSoftInputFromWindow(view.getWindowToken(),0);
  }
}

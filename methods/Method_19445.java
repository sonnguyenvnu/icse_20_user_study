@OnUnmount static void onUnmount(ComponentContext c,EditTextWithEventHandlers editText,@State AtomicReference<EditTextWithEventHandlers> mountedView){
  editText.setTextState(null);
  mountedView.set(null);
}

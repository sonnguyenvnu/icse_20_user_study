@OnUnmount static void onUnmount(ComponentContext c,EditTextWithEventHandlers editText,@State AtomicReference<EditTextWithEventHandlers> mountedView){
  mountedView.set(null);
}

@OnTrigger(DispatchKeyEvent.class) static void dispatchKey(ComponentContext c,@State AtomicReference<EditTextWithEventHandlers> mountedView,@FromTrigger KeyEvent keyEvent){
  EditTextWithEventHandlers view=mountedView.get();
  if (view != null) {
    view.dispatchKeyEvent(keyEvent);
  }
}

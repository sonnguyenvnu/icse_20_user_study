@OnUnbind static void onUnbind(final ComponentContext c,EditTextWithEventHandlers editText){
  editText.detachWatchers();
  editText.setComponentContext(null);
  editText.setTextChangedEventHandler(null);
  editText.setSelectionChangedEventHandler(null);
  editText.setKeyUpEventHandler(null);
  editText.setEditorActionEventHandler(null);
}

@OnUnbind static void onUnbind(ComponentContext c,EditTextWithEventHandlers editText){
  editText.detachWatchers();
  editText.clear();
}

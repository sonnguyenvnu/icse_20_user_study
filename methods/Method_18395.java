private static void unsetFocusChangeHandler(View view){
  final ComponentFocusChangeListener listener=getComponentFocusChangeListener(view);
  if (listener != null) {
    listener.setEventHandler(null);
  }
}

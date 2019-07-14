private static void unsetTouchHandler(View view){
  final ComponentTouchListener listener=getComponentTouchListener(view);
  if (listener != null) {
    listener.setEventHandler(null);
  }
}

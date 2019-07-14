private static void unsetClickHandler(View view){
  final ComponentClickListener listener=getComponentClickListener(view);
  if (listener != null) {
    listener.setEventHandler(null);
  }
}

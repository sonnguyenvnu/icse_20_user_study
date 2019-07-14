private static void unsetLongClickHandler(View view){
  final ComponentLongClickListener listener=getComponentLongClickListener(view);
  if (listener != null) {
    listener.setEventHandler(null);
  }
}

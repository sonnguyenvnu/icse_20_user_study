private void onListenerUpdate(boolean checked){
  if (listener != null) {
    listener.onCheckedChanged(this,checked);
  }
}

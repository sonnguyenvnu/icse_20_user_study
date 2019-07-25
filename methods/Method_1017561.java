@Override public void triggered(){
  for (Iterator<TriggerListener> iterator=getListeners().reverse(); iterator.hasNext(); ) {
    TriggerListener listener=iterator.next();
    listener.triggered();
  }
}

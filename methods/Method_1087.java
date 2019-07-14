public synchronized void removeListener(ControllerListener<? super INFO> listener){
  int index=mListeners.indexOf(listener);
  if (index != -1) {
    mListeners.set(index,null);
  }
}

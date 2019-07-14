@Override public synchronized TwitterStream clearListeners(){
  streamListeners.clear();
  updateListeners();
  return this;
}

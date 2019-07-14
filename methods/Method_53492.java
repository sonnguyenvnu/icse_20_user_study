@Override public synchronized TwitterStream replaceListener(StreamListener toBeRemoved,StreamListener toBeAdded){
  streamListeners.remove(toBeRemoved);
  streamListeners.add(toBeAdded);
  updateListeners();
  return this;
}

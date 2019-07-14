private synchronized void maybeInitStateContainers(){
  if (mStateContainers == null) {
    mStateContainers=new HashMap<>(INITIAL_MAP_CAPACITY);
  }
}

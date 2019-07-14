private synchronized void maybeInitNeededStateContainers(){
  if (mNeededStateContainers == null) {
    mNeededStateContainers=new HashSet<>();
  }
}

public boolean contains(SNode node){
  if (myDisabled) {
    return false;
  }
synchronized (myLock) {
    return myMap.values().contains(node) || myNodesWithoutRefs.contains(node);
  }
}

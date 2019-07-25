@Override public T put(SNode keyNode,T value){
  SNode representator=getKeyRepresentator(keyNode);
  if (representator == null) {
    for (    SNode node : myMap.keySet()) {
      if (MatchingUtil.matchNodes(node,keyNode)) {
        myRepresentatorsMap.put(keyNode,node);
        return myMap.put(node,value);
      }
    }
  }
  if (representator == null) {
    myRepresentatorsMap.put(keyNode,keyNode);
    myAbsentNodes.remove(keyNode);
    representator=keyNode;
  }
  return myMap.put(representator,value);
}

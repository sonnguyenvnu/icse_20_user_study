private void build(boolean withOpposite){
  Map<SNode,SNode> oldToNewMap=MapSequence.fromMap(new HashMap<SNode,SNode>());
  buildForNode(oldToNewMap,getOldNode(),getNewNode());
  for (  IMapping<SNode,SNode> node : MapSequence.fromMap(oldToNewMap)) {
    MapSequence.fromMap(myOldToNewMap).put(node.key().getNodeId(),node.value().getNodeId());
  }
  removeMatchedRefChanges();
  commit();
  if (withOpposite) {
    myChangeSet.buildOppositeChangeSet();
  }
}

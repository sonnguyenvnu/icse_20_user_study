@Override public void apply(@NotNull final SModel model,@NotNull final NodeCopier nodeCopier){
  prepare();
  ListSequence.fromList(myPreparedIdsToDelete).visitAll(new IVisitor<SNodeId>(){
    public void visit(    SNodeId id){
      check_yjf6x2_a0a0a0d0z(model.getNode(id));
    }
  }
);
  myPreparedIdsToDelete=null;
  Iterable<SNode> nodesToAdd=ListSequence.fromList(getChangedCollection(true)).page(myResultBegin,myResultEnd).select(new ISelector<SNode,SNode>(){
    public SNode select(    SNode child){
      return nodeCopier.copyNode(child);
    }
  }
);
  SNode beforeAnchor=(myBeforeAnchorId == null ? null : model.getNode(myBeforeAnchorId));
  SNode parent=model.getNode(myParentNodeId);
  for (  SNode newNode : Sequence.fromIterable(nodesToAdd)) {
    insertNodeBeforeAnchor(parent,newNode,beforeAnchor);
  }
}

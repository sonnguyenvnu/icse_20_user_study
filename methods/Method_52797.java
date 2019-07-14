private void computeNow(Node node){
  DataFlowNode inode=node.getDataFlowNode();
  List<VariableAccess> undefinitions=markUsages(inode);
  DataFlowNode firstINode=inode.getFlow().get(0);
  firstINode.setVariableAccess(undefinitions);
  DataFlowNode lastINode=inode.getFlow().get(inode.getFlow().size() - 1);
  lastINode.setVariableAccess(undefinitions);
}

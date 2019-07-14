public DataFlowNode getDoBranchNodeFromFirstDoStatement(){
  if (!isFirstDoStatement()) {
    return null;
  }
  DataFlowNode inode=getLast();
  for (  DataFlowNode parent : inode.getParents()) {
    if (parent.isType(NodeType.DO_EXPR)) {
      return parent;
    }
  }
  return null;
}

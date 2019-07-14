private boolean isFirstDoStatement(DataFlowNode inode){
  int index=inode.getIndex() - 1;
  if (index < 0) {
    return false;
  }
  return inode.getFlow().get(index).isType(NodeType.DO_BEFORE_FIRST_STATEMENT);
}

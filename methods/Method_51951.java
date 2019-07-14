private void addAccess(JavaNameOccurrence occurrence,DataFlowNode inode){
  if (occurrence.isOnLeftHandSide()) {
    this.addVariableAccess(occurrence.getLocation(),new VariableAccess(VariableAccess.DEFINITION,occurrence.getImage()),inode.getFlow());
  }
 else   if (occurrence.isOnRightHandSide() || !occurrence.isOnLeftHandSide() && !occurrence.isOnRightHandSide()) {
    this.addVariableAccess(occurrence.getLocation(),new VariableAccess(VariableAccess.REFERENCING,occurrence.getImage()),inode.getFlow());
  }
}

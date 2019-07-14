private List<VariableAccess> markUsages(DataFlowNode inode){
  List<VariableAccess> undefinitions=new ArrayList<>();
  Set<Map<NameDeclaration,List<NameOccurrence>>> variableDeclarations=collectDeclarations(inode);
  for (  Map<NameDeclaration,List<NameOccurrence>> declarations : variableDeclarations) {
    for (    Map.Entry<NameDeclaration,List<NameOccurrence>> entry : declarations.entrySet()) {
      NameDeclaration vnd=entry.getKey();
      if (vnd.getNode().jjtGetParent() instanceof ASTFormalParameter) {
        continue;
      }
 else       if (vnd.getNode().jjtGetParent().getFirstDescendantOfType(ASTVariableOrConstantInitializer.class) != null) {
        addVariableAccess(vnd.getNode(),new VariableAccess(VariableAccess.DEFINITION,vnd.getImage()),inode.getFlow());
      }
      undefinitions.add(new VariableAccess(VariableAccess.UNDEFINITION,vnd.getImage()));
      for (      NameOccurrence occurrence : entry.getValue()) {
        addAccess(occurrence,inode);
      }
    }
  }
  return undefinitions;
}

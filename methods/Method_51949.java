private List<VariableAccess> markUsages(DataFlowNode inode){
  List<VariableAccess> undefinitions=new ArrayList<>();
  Set<Map<VariableNameDeclaration,List<NameOccurrence>>> variableDeclarations=collectDeclarations(inode);
  for (  Map<VariableNameDeclaration,List<NameOccurrence>> declarations : variableDeclarations) {
    for (    Map.Entry<VariableNameDeclaration,List<NameOccurrence>> entry : declarations.entrySet()) {
      VariableNameDeclaration vnd=entry.getKey();
      if (vnd.getAccessNodeParent() instanceof ASTFormalParameter) {
        continue;
      }
 else       if (vnd.getAccessNodeParent().getFirstDescendantOfType(ASTVariableInitializer.class) != null) {
        addVariableAccess(vnd.getNode(),new VariableAccess(VariableAccess.DEFINITION,vnd.getImage()),inode.getFlow());
      }
      undefinitions.add(new VariableAccess(VariableAccess.UNDEFINITION,vnd.getImage()));
      for (      NameOccurrence occurrence : entry.getValue()) {
        addAccess((JavaNameOccurrence)occurrence,inode);
      }
    }
  }
  return undefinitions;
}

private Set<Map<VariableNameDeclaration,List<NameOccurrence>>> collectDeclarations(DataFlowNode inode){
  Set<Map<VariableNameDeclaration,List<NameOccurrence>>> decls=new HashSet<>();
  Map<VariableNameDeclaration,List<NameOccurrence>> varDecls;
  for (int i=0; i < inode.getFlow().size(); i++) {
    DataFlowNode n=inode.getFlow().get(i);
    if (n instanceof StartOrEndDataFlowNode) {
      continue;
    }
    varDecls=((JavaNode)n.getNode()).getScope().getDeclarations(VariableNameDeclaration.class);
    if (!decls.contains(varDecls)) {
      decls.add(varDecls);
    }
  }
  return decls;
}

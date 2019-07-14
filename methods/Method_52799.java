private Set<Map<NameDeclaration,List<NameOccurrence>>> collectDeclarations(DataFlowNode inode){
  Set<Map<NameDeclaration,List<NameOccurrence>>> decls=new HashSet<>();
  Map<NameDeclaration,List<NameOccurrence>> varDecls;
  for (int i=0; i < inode.getFlow().size(); i++) {
    DataFlowNode n=inode.getFlow().get(i);
    if (n instanceof StartOrEndDataFlowNode) {
      continue;
    }
    varDecls=((PLSQLNode)n.getNode()).getScope().getDeclarations();
    if (!decls.contains(varDecls)) {
      decls.add(varDecls);
    }
  }
  return decls;
}

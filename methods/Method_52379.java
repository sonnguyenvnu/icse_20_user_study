private List<NameOccurrence> determineUsages(ASTVariableDeclaratorId node){
  Map<VariableNameDeclaration,List<NameOccurrence>> decls=node.getScope().getDeclarations(VariableNameDeclaration.class);
  for (  Map.Entry<VariableNameDeclaration,List<NameOccurrence>> entry : decls.entrySet()) {
    if (node.hasImageEqualTo(entry.getKey().getName())) {
      return entry.getValue();
    }
  }
  return Collections.emptyList();
}

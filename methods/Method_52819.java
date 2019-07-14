@Override public Set<NameDeclaration> addNameOccurrence(NameOccurrence occ){
  PLSQLNameOccurrence occurrence=(PLSQLNameOccurrence)occ;
  Set<NameDeclaration> declarations=findVariableHere(occurrence);
  Map<MethodNameDeclaration,List<NameOccurrence>> methodNames=getMethodDeclarations();
  if (!declarations.isEmpty() && occurrence.isMethodOrConstructorInvocation()) {
    for (    NameDeclaration decl : declarations) {
      List<NameOccurrence> nameOccurrences=methodNames.get(decl);
      if (nameOccurrences == null) {
      }
 else {
        nameOccurrences.add(occurrence);
        Node n=occurrence.getLocation();
        if (n instanceof ASTName) {
          ((ASTName)n).setNameDeclaration(decl);
        }
      }
    }
  }
 else   if (!declarations.isEmpty() && !occurrence.isThisOrSuper()) {
    Map<VariableNameDeclaration,List<NameOccurrence>> variableNames=getVariableDeclarations();
    for (    NameDeclaration decl : declarations) {
      List<NameOccurrence> nameOccurrences=variableNames.get(decl);
      if (nameOccurrences == null) {
      }
 else {
        nameOccurrences.add(occurrence);
        Node n=occurrence.getLocation();
        if (n instanceof ASTName) {
          ((ASTName)n).setNameDeclaration(decl);
        }
      }
    }
  }
  return declarations;
}

@Override public Set<NameDeclaration> addNameOccurrence(NameOccurrence occurrence){
  JavaNameOccurrence javaOccurrence=(JavaNameOccurrence)occurrence;
  Set<NameDeclaration> declarations=findVariableHere(javaOccurrence);
  if (!declarations.isEmpty() && !javaOccurrence.isThisOrSuper()) {
    for (    NameDeclaration decl : declarations) {
      getVariableDeclarations().get(decl).add(javaOccurrence);
      Node n=javaOccurrence.getLocation();
      if (n instanceof ASTName) {
        ((ASTName)n).setNameDeclaration(decl);
      }
    }
  }
  return declarations;
}

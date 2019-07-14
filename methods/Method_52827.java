@Override public Set<NameDeclaration> addNameOccurrence(NameOccurrence occ){
  PLSQLNameOccurrence occurrence=(PLSQLNameOccurrence)occ;
  Set<NameDeclaration> declarations=findVariableHere(occurrence);
  if (!declarations.isEmpty() && !occurrence.isThisOrSuper()) {
    for (    NameDeclaration decl : declarations) {
      getVariableDeclarations().get(decl).add(occurrence);
      Node n=occurrence.getLocation();
      if (n instanceof ASTName) {
        ((ASTName)n).setNameDeclaration(decl);
      }
    }
  }
  return declarations;
}

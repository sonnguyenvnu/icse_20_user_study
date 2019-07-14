@Override public Set<NameDeclaration> addNameOccurrence(NameOccurrence occurrence){
  JavaNameOccurrence javaOccurrence=(JavaNameOccurrence)occurrence;
  Set<NameDeclaration> declarations=findVariableHere(javaOccurrence);
  if (!declarations.isEmpty() && (javaOccurrence.isMethodOrConstructorInvocation() || javaOccurrence.isMethodReference())) {
    for (    NameDeclaration decl : declarations) {
      List<NameOccurrence> nameOccurrences=getMethodDeclarations().get(decl);
      if (nameOccurrences == null) {
        for (        ClassNameDeclaration innerClass : getClassDeclarations().keySet()) {
          Scope innerClassScope=innerClass.getScope();
          if (innerClassScope.contains(javaOccurrence)) {
            innerClassScope.addNameOccurrence(javaOccurrence);
          }
        }
      }
 else {
        nameOccurrences.add(javaOccurrence);
        Node n=javaOccurrence.getLocation();
        if (n instanceof ASTName) {
          ((ASTName)n).setNameDeclaration(decl);
        }
      }
    }
  }
 else   if (!declarations.isEmpty() && !javaOccurrence.isThisOrSuper()) {
    for (    NameDeclaration decl : declarations) {
      List<NameOccurrence> nameOccurrences=getVariableDeclarations().get(decl);
      if (nameOccurrences == null) {
        for (        ClassNameDeclaration innerClass : getClassDeclarations().keySet()) {
          Scope innerClassScope=innerClass.getScope();
          if (innerClassScope.contains(javaOccurrence)) {
            innerClassScope.addNameOccurrence(javaOccurrence);
          }
        }
      }
 else {
        nameOccurrences.add(javaOccurrence);
        Node n=javaOccurrence.getLocation();
        if (n instanceof ASTName) {
          ((ASTName)n).setNameDeclaration(decl);
        }
      }
    }
  }
  return declarations;
}

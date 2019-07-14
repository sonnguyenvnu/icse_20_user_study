private boolean isLocalAttributeAccess(String varName,Scope scope){
  Scope currentScope=scope;
  while (currentScope != null) {
    for (    VariableNameDeclaration decl : currentScope.getDeclarations(VariableNameDeclaration.class).keySet()) {
      if (decl.getImage().equals(varName)) {
        if (currentScope instanceof ClassScope && ((ClassScope)currentScope).getClassDeclaration().getNode() == exploredClass) {
          return true;
        }
      }
    }
    currentScope=currentScope.getParent();
  }
  return false;
}

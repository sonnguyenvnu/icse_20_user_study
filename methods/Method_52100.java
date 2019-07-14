private Entry<VariableNameDeclaration,List<NameOccurrence>> findDeclaration(String varName,Scope innermost){
  Scope currentScope=innermost;
  while (currentScope != null) {
    for (    Entry<VariableNameDeclaration,List<NameOccurrence>> e : currentScope.getDeclarations(VariableNameDeclaration.class).entrySet()) {
      if (e.getKey().getName().equals(varName)) {
        return e;
      }
    }
    currentScope=currentScope.getParent();
  }
  return null;
}

private boolean isInitDataModifiedAfterInit(final VariableNameDeclaration variableDeclaration,final ASTReturnStatement rtn){
  final ASTVariableInitializer initializer=variableDeclaration.getAccessNodeParent().getFirstDescendantOfType(ASTVariableInitializer.class);
  if (initializer != null) {
    final ASTBlockStatement initializerStmt=variableDeclaration.getAccessNodeParent().getFirstParentOfType(ASTBlockStatement.class);
    final ASTBlockStatement rtnStmt=rtn.getFirstParentOfType(ASTBlockStatement.class);
    final List<ASTName> referencedNames=initializer.findDescendantsOfType(ASTName.class);
    for (    final ASTName refName : referencedNames) {
      Scope scope=refName.getScope();
      do {
        final Map<VariableNameDeclaration,List<NameOccurrence>> declarations=scope.getDeclarations(VariableNameDeclaration.class);
        for (        final Map.Entry<VariableNameDeclaration,List<NameOccurrence>> entry : declarations.entrySet()) {
          if (entry.getKey().getName().equals(refName.getImage())) {
            for (            final NameOccurrence occ : entry.getValue()) {
              final ASTBlockStatement location=occ.getLocation().getFirstParentOfType(ASTBlockStatement.class);
              if (location != null && isAfter(location,initializerStmt) && isAfter(rtnStmt,location)) {
                return true;
              }
            }
            return false;
          }
        }
        scope=scope.getParent();
      }
 while (scope != null);
    }
  }
  return false;
}

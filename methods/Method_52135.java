private ASTClassOrInterfaceType getTypeOfMethodCall(ASTPrimarySuffix node){
  ASTClassOrInterfaceType type=null;
  ASTName methodName=node.jjtGetParent().getFirstChildOfType(ASTPrimaryPrefix.class).getFirstChildOfType(ASTName.class);
  if (methodName != null) {
    ClassScope classScope=node.getScope().getEnclosingScope(ClassScope.class);
    Map<MethodNameDeclaration,List<NameOccurrence>> methods=classScope.getMethodDeclarations();
    for (    Map.Entry<MethodNameDeclaration,List<NameOccurrence>> e : methods.entrySet()) {
      if (e.getKey().getName().equals(methodName.getImage())) {
        type=e.getKey().getNode().getFirstParentOfType(ASTMethodDeclaration.class).getFirstChildOfType(ASTResultType.class).getFirstDescendantOfType(ASTClassOrInterfaceType.class);
        break;
      }
    }
  }
  return type;
}

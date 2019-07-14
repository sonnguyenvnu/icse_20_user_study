private SimpleTypedNameDeclaration determineSuper(Node declaringNode){
  SimpleTypedNameDeclaration result=null;
  if (declaringNode instanceof ASTClassOrInterfaceDeclaration) {
    ASTClassOrInterfaceDeclaration classDeclaration=(ASTClassOrInterfaceDeclaration)declaringNode;
    ASTImplementsList implementsList=classDeclaration.getFirstChildOfType(ASTImplementsList.class);
    if (implementsList != null) {
      List<ASTClassOrInterfaceType> types=implementsList.findChildrenOfType(ASTClassOrInterfaceType.class);
      SimpleTypedNameDeclaration type=convertToSimpleType(types);
      result=type;
    }
    ASTExtendsList extendsList=classDeclaration.getFirstChildOfType(ASTExtendsList.class);
    if (extendsList != null) {
      List<ASTClassOrInterfaceType> types=extendsList.findChildrenOfType(ASTClassOrInterfaceType.class);
      SimpleTypedNameDeclaration type=convertToSimpleType(types);
      if (result == null) {
        result=type;
      }
 else {
        result.addNext(type);
      }
    }
  }
  return result;
}

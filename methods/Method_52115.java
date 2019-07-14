private boolean isSerializationMethod(ASTMethodDeclaration node){
  ASTMethodDeclarator declarator=node.getFirstDescendantOfType(ASTMethodDeclarator.class);
  List<ASTFormalParameter> parameters=declarator.findDescendantsOfType(ASTFormalParameter.class);
  if (node.isPrivate() && "readObject".equals(node.getMethodName()) && parameters.size() == 1 && throwsOneException(node,InvalidObjectException.class)) {
    ASTType type=parameters.get(0).getTypeNode();
    if (type.getType() == ObjectInputStream.class || ObjectInputStream.class.getSimpleName().equals(type.getTypeImage()) || ObjectInputStream.class.getName().equals(type.getTypeImage())) {
      return true;
    }
  }
  return false;
}

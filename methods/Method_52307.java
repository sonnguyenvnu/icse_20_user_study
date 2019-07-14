private boolean isBeanAccessor(ASTMethodDeclarator meth){
  String methodName=meth.getImage();
  if (methodName.startsWith("get") || methodName.startsWith("set")) {
    return true;
  }
  if (methodName.startsWith("is")) {
    ASTResultType ret=((ASTMethodDeclaration)meth.jjtGetParent()).getResultType();
    List<ASTPrimitiveType> primitives=ret.findDescendantsOfType(ASTPrimitiveType.class);
    if (!primitives.isEmpty() && primitives.get(0).isBoolean()) {
      return true;
    }
  }
  return false;
}

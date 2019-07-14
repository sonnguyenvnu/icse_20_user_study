@Override public Object visit(ASTMethodDeclaration node,Object data){
  if (!node.isPrivate() && !getProperty(CHECKALL_DESCRIPTOR)) {
    return data;
  }
  if (!node.isNative() && !node.isAbstract() && !isSerializationMethod(node) && !hasOverrideAnnotation(node)) {
    check(node,data);
  }
  return data;
}

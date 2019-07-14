public boolean isCloneMethod(final ASTMethodDeclarator method){
  if (!"clone".equals(method.getImage())) {
    return false;
  }
  return method.getParameterCount() == 0;
}

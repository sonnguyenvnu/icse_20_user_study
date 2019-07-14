public boolean isJUnitMethod(ASTMethodDeclaration method,Object data){
  if (method.isAbstract() || method.isNative() || method.isStatic()) {
    return false;
  }
  if (!isJUnit5Class && !method.isPublic()) {
    return false;
  }
  boolean result=false;
  result|=isJUnit3Method(method);
  result|=isJUnit4Method(method);
  result|=isJUnit5Method(method);
  return result;
}

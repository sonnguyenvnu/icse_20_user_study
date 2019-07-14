private boolean hasEqualsInClassHierarchy(TypeElement clazz){
  ExecutableElement methodOnClass=getMethodOnClass(clazz,EQUALS_METHOD,typeUtils,elements);
  if (methodOnClass == null) {
    return false;
  }
  Element implementingClass=methodOnClass.getEnclosingElement();
  if (implementingClass.getSimpleName().toString().equals("Object")) {
    return false;
  }
  return true;
}

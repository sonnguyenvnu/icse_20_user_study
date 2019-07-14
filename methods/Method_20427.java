/** 
 * @return True if the clazz (or one of its superclasses) implements the given method. Returnsfalse if the method doesn't exist anywhere in the class hierarchy or it is abstract.
 */
static boolean implementsMethod(TypeElement clazz,MethodSpec method,Types typeUtils,Elements elements){
  ExecutableElement methodOnClass=getMethodOnClass(clazz,method,typeUtils,elements);
  if (methodOnClass == null) {
    return false;
  }
  Set<Modifier> modifiers=methodOnClass.getModifiers();
  return !modifiers.contains(Modifier.ABSTRACT);
}

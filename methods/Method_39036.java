private boolean parseMethodAuthFlag(final Method actionMethod){
  if (actionMethod.getAnnotation(Auth.class) != null) {
    return true;
  }
  final Class declaringClass=actionMethod.getDeclaringClass();
  if (declaringClass.getAnnotation(Auth.class) != null) {
    return true;
  }
  if (declaringClass.getPackage().getAnnotation(Auth.class) != null) {
    return true;
  }
  return false;
}

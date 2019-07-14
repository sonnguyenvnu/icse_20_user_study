/** 
 * Returns true when the return value should be propagated. Use a default otherwise. 
 */
private static boolean validateReturnType(Method method,Class<?> expected){
  Class<?> returnType=method.getReturnType();
  if (returnType == void.class) {
    return false;
  }
  if (returnType != expected) {
    String expectedType="'" + expected.getName() + "'";
    if (expected != void.class) {
      expectedType="'void' or " + expectedType;
    }
    throw new IllegalStateException(method.getDeclaringClass().getName() + "." + method.getName() + " must have return type of " + expectedType);
  }
  return true;
}

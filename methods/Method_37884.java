/** 
 * Returns <code>true</code> if method is a bean property.
 */
public static boolean isBeanProperty(final Method method){
  if (isObjectMethod(method)) {
    return false;
  }
  String methodName=method.getName();
  Class returnType=method.getReturnType();
  Class[] paramTypes=method.getParameterTypes();
  if (methodName.startsWith(METHOD_GET_PREFIX)) {
    if ((returnType != null) && (paramTypes.length == 0)) {
      return true;
    }
  }
 else   if (methodName.startsWith(METHOD_IS_PREFIX)) {
    if ((returnType != null) && (paramTypes.length == 0)) {
      return true;
    }
  }
 else   if (methodName.startsWith(METHOD_SET_PREFIX)) {
    if (paramTypes.length == 1) {
      return true;
    }
  }
  return false;
}

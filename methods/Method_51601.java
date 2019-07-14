/** 
 * Attempts to return the specified method from the class provided but will walk up its superclasses until it finds a match. Returns null if it doesn't.
 * @param clasz Class
 * @param methodName String
 * @param paramTypes Class[]
 * @return Method
 */
public static Method methodFor(Class<?> clasz,String methodName,Class<?>[] paramTypes){
  Method method=null;
  Class<?> current=clasz;
  while (current != Object.class) {
    try {
      method=current.getDeclaredMethod(methodName,paramTypes);
    }
 catch (    NoSuchMethodException ex) {
      current=current.getSuperclass();
    }
    if (method != null) {
      return method;
    }
  }
  return null;
}

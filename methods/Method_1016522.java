/** 
 * Returns whether  {@code type} overrides method {@code methodName(params)}. 
 */
public static boolean overrides(TypeElement type,Types types,String methodName,TypeMirror... params){
  return override(type,types,methodName,params).isPresent();
}

/** 
 * Returns the method on  {@code type} that overrides method {@code methodName(params)}. 
 */
public static Optional<ExecutableElement> override(TypeElement type,Types types,String methodName,TypeMirror... params){
  return methodsIn(type.getEnclosedElements()).stream().filter(method -> signatureMatches(method,types,methodName,params)).findAny();
}

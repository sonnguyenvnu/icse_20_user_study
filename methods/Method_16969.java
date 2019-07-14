/** 
 * Creates a public static field with an Unsafe address offset. 
 */
public static FieldSpec newFieldOffset(String className,String varName){
  String fieldName=CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE,varName);
  return FieldSpec.builder(long.class,offsetName(varName),Modifier.PROTECTED,Modifier.STATIC,Modifier.FINAL).initializer("$T.objectFieldOffset($T.class, $L.$L)",UNSAFE_ACCESS,ClassName.bestGuess(className),LOCAL_CACHE_FACTORY,fieldName).build();
}

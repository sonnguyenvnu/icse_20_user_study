/** 
 * Looks for potentially applicable methods in a given type definition.
 */
public static List<MethodType> getApplicableMethods(JavaTypeDefinition context,String methodName,List<JavaTypeDefinition> typeArguments,int argArity,Class<?> accessingClass){
  List<MethodType> result=new ArrayList<>();
  if (context == null) {
    return result;
  }
  Class<?> contextClass=context.getType();
  try {
    for (    Method method : contextClass.getDeclaredMethods()) {
      if (isMethodApplicable(method,methodName,argArity,accessingClass,typeArguments)) {
        result.add(getTypeDefOfMethod(context,method,typeArguments));
      }
    }
  }
 catch (  final LinkageError ignored) {
  }
  if (!contextClass.equals(Object.class)) {
    List<MethodType> inheritedMethods=getApplicableMethods(context.resolveTypeDefinition(contextClass.getGenericSuperclass()),methodName,typeArguments,argArity,accessingClass);
    for (    MethodType inherited : inheritedMethods) {
      if (!result.contains(inherited)) {
        result.add(inherited);
      }
    }
  }
  for (  Type interfaceType : contextClass.getGenericInterfaces()) {
    result.addAll(getApplicableMethods(context.resolveTypeDefinition(interfaceType),methodName,typeArguments,argArity,accessingClass));
  }
  return result;
}

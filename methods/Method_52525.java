public static MethodType getTypeDefOfMethod(JavaTypeDefinition context,Method method,List<JavaTypeDefinition> typeArguments){
  if (typeArguments.isEmpty() && isGeneric(method)) {
    return MethodType.build(method);
  }
  JavaTypeDefinition returnType=context.resolveTypeDefinition(method.getGenericReturnType(),method,typeArguments);
  List<JavaTypeDefinition> argTypes=new ArrayList<>();
  for (  Type argType : method.getGenericParameterTypes()) {
    argTypes.add(context.resolveTypeDefinition(argType,method,typeArguments));
  }
  return MethodType.build(returnType,argTypes,method);
}

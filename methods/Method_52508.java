public static MethodType build(JavaTypeDefinition returnType,List<JavaTypeDefinition> argTypes,Method method){
  return new MethodType(returnType,Collections.unmodifiableList(argTypes),method);
}

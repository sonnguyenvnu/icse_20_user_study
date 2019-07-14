@Override public JavaTypeDefinition resolveTypeDefinition(Type type,Method method,List<JavaTypeDefinition> methodTypeArgs){
  return firstJavaType().resolveTypeDefinition(type,method,methodTypeArgs);
}

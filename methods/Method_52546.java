private JavaTypeDefinition getGenericType(final String parameterName,Method method,List<JavaTypeDefinition> methodTypeArguments){
  if (method != null && methodTypeArguments != null) {
    int paramIndex=getGenericTypeIndex(method.getTypeParameters(),parameterName);
    if (paramIndex != -1) {
      return methodTypeArguments.get(paramIndex);
    }
  }
  return getGenericType(parameterName);
}

private static TypeName getBaseType(TypeName typeName){
  if (typeName instanceof ParameterizedTypeName) {
    return ((ParameterizedTypeName)typeName).rawType;
  }
  return typeName;
}

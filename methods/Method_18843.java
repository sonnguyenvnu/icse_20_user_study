private static TypeName findTypeByTypeName(final TypeName typeName){
  if (typeName instanceof ParameterizedTypeName) {
    return ((ParameterizedTypeName)typeName).rawType;
  }
  return typeName;
}

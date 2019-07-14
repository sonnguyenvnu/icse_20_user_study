private static TypeName getWithParams(@NonNull ClassName baseType,@NonNull ParameterizedTypeName parameterType){
  return ParameterizedTypeName.get(baseType,parameterType.typeArguments.toArray(new TypeName[parameterType.typeArguments.size()]));
}

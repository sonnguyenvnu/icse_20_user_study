static TypeName getRawType(TypeName type){
  return type instanceof ParameterizedTypeName ? ((ParameterizedTypeName)type).rawType : type;
}

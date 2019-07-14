@NonNull public static TypeName getImmutableType(TypeName type){
  if (type instanceof ParameterizedTypeName) {
    final TypeName genericType=((ParameterizedTypeName)type).rawType;
    if (MAP.equals(genericType)) {
      type=getWithParams(IMMUTABLE_MAP,(ParameterizedTypeName)type);
    }
 else     if (SET.equals(genericType)) {
      return getWithParams(IMMUTABLE_SET,(ParameterizedTypeName)type);
    }
 else     if (LIST.equals(genericType)) {
      type=getWithParams(IMMUTABLE_LIST,(ParameterizedTypeName)type);
    }
  }
 else   if (type instanceof ArrayTypeName) {
    type=ParameterizedTypeName.get(IMMUTABLE_LIST,((ArrayTypeName)type).componentType);
  }
  return type;
}

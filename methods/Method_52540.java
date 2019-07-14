public static JavaTypeDefinition forClass(TypeDefinitionType type,Class<?> clazz,JavaTypeDefinition... boundGenerics){
  return forClass(type,forClass(clazz,boundGenerics));
}

public static JavaTypeDefinition forClass(final Class<?> clazz,JavaTypeDefinition... boundGenerics){
  if (clazz == null) {
    return null;
  }
  if (boundGenerics.length != 0) {
    return new JavaTypeDefinitionSimple(clazz,boundGenerics);
  }
  final JavaTypeDefinition typeDef=CLASS_EXACT_TYPE_DEF_CACHE.get(clazz);
  if (typeDef != null) {
    return typeDef;
  }
  final JavaTypeDefinition newDef;
  try {
    newDef=new JavaTypeDefinitionSimple(clazz);
  }
 catch (  final NoClassDefFoundError e) {
    return null;
  }
  CLASS_EXACT_TYPE_DEF_CACHE.put(clazz,newDef);
  return newDef;
}

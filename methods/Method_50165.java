/** 
 * Returns an  {@link ImmutableSet} of all the fields in {@code moduleClass} or its super classesthat have the expected type and annotation.
 */
private static ImmutableSet<Field> findFields(Class<?> moduleClass,Class<? extends Annotation> targetAnnotation,Class<?> expectedType){
  Class<?> clazz=moduleClass;
  ImmutableSet.Builder<Field> nodesBuilder=ImmutableSet.builder();
  while (clazz != null && !SchemaDefinitionReader.class.equals(clazz)) {
    for (    Field method : clazz.getDeclaredFields()) {
      if (method.isAnnotationPresent(targetAnnotation)) {
        Preconditions.checkArgument(method.getType() == expectedType,"Field %s should be type %s",method,expectedType.getSimpleName());
        nodesBuilder.add(method);
      }
    }
    clazz=clazz.getSuperclass();
  }
  return nodesBuilder.build();
}

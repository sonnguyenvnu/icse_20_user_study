/** 
 * Returns an  {@link ImmutableSet} of all the methods in {@code moduleClass} or its super classesthat have the expected type and annotation.
 */
private static ImmutableSet<Method> findMethods(Class<?> moduleClass,Class<? extends Annotation> targetAnnotation){
  Class<?> clazz=moduleClass;
  ImmutableSet.Builder<Method> nodesBuilder=ImmutableSet.builder();
  while (clazz != null && !SchemaDefinitionReader.class.equals(clazz)) {
    for (    Method method : clazz.getDeclaredMethods()) {
      if (method.isAnnotationPresent(targetAnnotation)) {
        nodesBuilder.add(method);
      }
    }
    clazz=clazz.getSuperclass();
  }
  return nodesBuilder.build();
}

/** 
 * Returns an  {@link ImmutableSet} of all the methods in {@code moduleClass} or its super classesthat have the expected type and annotation.
 */
private static <T extends Annotation>T findClassAnnotation(Class<?> moduleClass,Class<T> targetAnnotation){
  Class<?> clazz=moduleClass;
  while (clazz != null && !SchemaDefinitionReader.class.equals(clazz)) {
    T annotation=clazz.getAnnotation(targetAnnotation);
    if (annotation != null) {
      return annotation;
    }
    clazz=clazz.getSuperclass();
  }
  return null;
}

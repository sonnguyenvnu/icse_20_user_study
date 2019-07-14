private static <T>boolean withExtensionAnnotation(final Class<T> type){
  return type.isAnnotationPresent(HmilySPI.class);
}

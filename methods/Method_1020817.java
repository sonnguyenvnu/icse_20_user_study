public static Annotation[] annotation(Object obj,String fieldName) throws Exception {
  Field field=obj.getClass().getDeclaredField(fieldName);
  return field.getAnnotations();
}

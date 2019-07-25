private void inject(Set<Field> fields){
  for (  final Field field : fields) {
    if (field.isAnnotationPresent(Inject.class)) {
      final AnnotatedField<T> annotatedField=new AnnotatedFieldImpl<>(field);
      field.setAccessible(true);
      annotatedFields.add(annotatedField);
    }
  }
}

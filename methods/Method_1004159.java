public static NewBindingKey get(TypeName typeName,@Nullable AnnotationSpec qualifier){
  return new NewBindingKey(typeName,qualifier);
}

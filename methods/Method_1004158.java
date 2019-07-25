public static NewBindingKey get(TypeName typeName,@Nullable AnnotationMirror qualifier){
  return new NewBindingKey(typeName,qualifier);
}

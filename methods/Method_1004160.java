public static NewBindingKey get(TypeMirror typeMirror,@Nullable AnnotationSpec qualifier){
  return new NewBindingKey(typeMirror,qualifier);
}

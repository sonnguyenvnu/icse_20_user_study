@NonNull public Element fromStringResourceAnnotationMethod(@NonNull ExecutableElement method){
  final Pair<List<AnnotationSpec>,Set<ClassName>> annotations=getAnnotations(method);
  return new AnnotationField.StringResource(method.getSimpleName().toString(),annotations.getLeft(),annotations.getRight(),method.getDefaultValue(),elements.getDocComment(method));
}

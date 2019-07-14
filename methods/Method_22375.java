@NonNull public Element fromAnnotationMethod(@NonNull ExecutableElement method){
  final Pair<List<AnnotationSpec>,Set<ClassName>> annotations=getAnnotations(method);
  return new AnnotationField.Normal(method.getSimpleName().toString(),TypeName.get(method.getReturnType()),annotations.getLeft(),annotations.getRight(),method.getDefaultValue(),elements.getDocComment(method));
}

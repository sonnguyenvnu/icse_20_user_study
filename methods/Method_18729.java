public static ImmutableList<AnnotationSpec> extractValidAnnotations(Project project,PsiClass psiClass){
  final List<AnnotationSpec> annotations=new ArrayList<>();
  for (  PsiAnnotation annotation : psiClass.getModifierList().getAnnotations()) {
    if (isValidAnnotation(project,annotation)) {
      annotations.add(AnnotationSpec.builder(ClassName.bestGuess(annotation.getQualifiedName())).build());
    }
  }
  return ImmutableList.copyOf(annotations);
}

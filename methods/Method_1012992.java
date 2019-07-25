private void warn(final PsiElement psiElement,final AnnotationHolder annotationHolder,final PsiElement searchableCurrentElement,final String warning){
  final PsiReference first=ReferencesSearch.search(searchableCurrentElement).findFirst();
  if (first == null) {
    Annotation annotation=annotationHolder.createWeakWarningAnnotation(psiElement,warning);
    annotation.setHighlightType(ProblemHighlightType.LIKE_UNUSED_SYMBOL);
  }
}

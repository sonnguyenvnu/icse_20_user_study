public static ImmutableList<EventDeclarationModel> getEventDeclarations(Project project,PsiClass psiClass){
  final PsiAnnotation layoutSpecAnnotation=AnnotationUtil.findAnnotation(psiClass,"com.facebook.litho.annotations.LayoutSpec");
  if (layoutSpecAnnotation == null) {
    throw new RuntimeException("LayoutSpec annotation not found on class");
  }
  PsiAnnotationMemberValue psiAnnotationMemberValue=layoutSpecAnnotation.findAttributeValue("events");
  ArrayList<EventDeclarationModel> eventDeclarationModels=new ArrayList<>();
  if (psiAnnotationMemberValue instanceof PsiArrayInitializerMemberValue) {
    PsiArrayInitializerMemberValue value=(PsiArrayInitializerMemberValue)psiAnnotationMemberValue;
    for (    PsiAnnotationMemberValue annotationMemberValue : value.getInitializers()) {
      PsiClassObjectAccessExpression accessExpression=(PsiClassObjectAccessExpression)annotationMemberValue;
      eventDeclarationModels.add(getEventDeclarationModel(project,accessExpression));
    }
  }
 else {
    PsiClassObjectAccessExpression accessExpression=(PsiClassObjectAccessExpression)psiAnnotationMemberValue;
    eventDeclarationModels.add(getEventDeclarationModel(project,accessExpression));
  }
  return ImmutableList.copyOf(eventDeclarationModels);
}

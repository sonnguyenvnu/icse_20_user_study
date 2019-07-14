public static ImmutableList<SpecMethodModel<EventMethod,EventDeclarationModel>> getOnEventMethods(Project project,PsiClass psiClass,List<Class<? extends Annotation>> permittedInterStageInputAnnotations){
  final List<SpecMethodModel<EventMethod,EventDeclarationModel>> delegateMethods=new ArrayList<>();
  for (  PsiMethod psiMethod : psiClass.getMethods()) {
    final PsiAnnotation onEventAnnotation=AnnotationUtil.findAnnotation(psiMethod,"com.facebook.litho.annotations.OnEvent");
    if (onEventAnnotation != null) {
      final List<MethodParamModel> methodParams=getMethodParams(psiMethod,EventMethodExtractor.getPermittedMethodParamAnnotations(permittedInterStageInputAnnotations),permittedInterStageInputAnnotations,ImmutableList.<Class<? extends Annotation>>of());
      PsiAnnotationMemberValue psiAnnotationMemberValue=onEventAnnotation.findAttributeValue("value");
      PsiClassObjectAccessExpression accessExpression=(PsiClassObjectAccessExpression)psiAnnotationMemberValue;
      final SpecMethodModel<EventMethod,EventDeclarationModel> eventMethod=new SpecMethodModel<>(ImmutableList.<Annotation>of(),PsiProcessingUtils.extractModifiers(psiMethod.getModifierList()),psiMethod.getName(),PsiTypeUtils.generateTypeSpec(psiMethod.getReturnType()),ImmutableList.copyOf(getTypeVariables(psiMethod)),ImmutableList.copyOf(methodParams),psiMethod,PsiEventDeclarationsExtractor.getEventDeclarationModel(project,accessExpression));
      delegateMethods.add(eventMethod);
    }
  }
  return ImmutableList.copyOf(delegateMethods);
}

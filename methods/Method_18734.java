public static ImmutableList<SpecMethodModel<DelegateMethod,Void>> getDelegateMethods(PsiClass psiClass,List<Class<? extends Annotation>> permittedMethodAnnotations,List<Class<? extends Annotation>> permittedInterStageInputAnnotations,List<Class<? extends Annotation>> delegateMethodAnnotationsThatSkipDiffModels){
  final List<SpecMethodModel<DelegateMethod,Void>> delegateMethods=new ArrayList<>();
  for (  PsiMethod psiMethod : psiClass.getMethods()) {
    final List<Annotation> methodAnnotations=getMethodAnnotations(psiMethod,permittedMethodAnnotations);
    if (!methodAnnotations.isEmpty()) {
      final List<MethodParamModel> methodParams=getMethodParams(psiMethod,DelegateMethodExtractor.getPermittedMethodParamAnnotations(permittedInterStageInputAnnotations),permittedInterStageInputAnnotations,delegateMethodAnnotationsThatSkipDiffModels);
      final SpecMethodModel<DelegateMethod,Void> delegateMethod=new SpecMethodModel<>(ImmutableList.copyOf(methodAnnotations),PsiProcessingUtils.extractModifiers(psiMethod.getModifierList()),psiMethod.getName(),PsiTypeUtils.generateTypeSpec(psiMethod.getReturnType()),ImmutableList.<TypeVariableName>of(),ImmutableList.copyOf(methodParams),psiMethod,null);
      delegateMethods.add(delegateMethod);
    }
  }
  return ImmutableList.copyOf(delegateMethods);
}

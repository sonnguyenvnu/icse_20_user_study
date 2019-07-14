@Nullable private static SpecMethodModel<EventMethod,WorkingRangeDeclarationModel> generateWorkingRangeMethod(PsiMethod psiMethod,List<Class<? extends Annotation>> permittedInterStageInputAnnotations,String annotation){
  final List<MethodParamModel> methodParams=getMethodParams(psiMethod,getPermittedMethodParamAnnotations(permittedInterStageInputAnnotations),permittedInterStageInputAnnotations,ImmutableList.<Class<? extends Annotation>>of());
  PsiAnnotation psiOnEnteredRangeAnnotation=AnnotationUtil.findAnnotation(psiMethod,annotation);
  PsiNameValuePair valuePair=AnnotationUtil.findDeclaredAttribute(psiOnEnteredRangeAnnotation,"name");
  PsiClassObjectAccessExpression valueClassExpression=(PsiClassObjectAccessExpression)valuePair.getValue();
  PsiType valueType=valueClassExpression.getOperand().getType();
  PsiClass valueClass=PsiTypesUtil.getPsiClass(valueType);
  return SpecMethodModel.<EventMethod,WorkingRangeDeclarationModel>builder().annotations(ImmutableList.of()).modifiers(PsiProcessingUtils.extractModifiers(psiMethod.getModifierList())).name(psiMethod.getName()).returnTypeSpec(new TypeSpec(TypeName.VOID)).typeVariables(ImmutableList.copyOf(getTypeVariables(psiMethod))).methodParams(ImmutableList.copyOf(methodParams)).representedObject(psiMethod).typeModel(new WorkingRangeDeclarationModel(valuePair.getLiteralValue(),valueClass)).build();
}

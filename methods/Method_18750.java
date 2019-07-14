public static ImmutableList<SpecMethodModel<EventMethod,EventDeclarationModel>> getOnTriggerMethods(PsiClass psiClass,List<Class<? extends Annotation>> permittedInterStageInputAnnotations){
  final List<SpecMethodModel<EventMethod,EventDeclarationModel>> delegateMethods=new ArrayList<>();
  for (  PsiMethod psiMethod : psiClass.getMethods()) {
    final OnTrigger onTriggerAnnotation=AnnotationUtil.findAnnotationInHierarchy(psiMethod,OnTrigger.class);
    if (onTriggerAnnotation != null) {
      final List<MethodParamModel> methodParams=getMethodParams(psiMethod,getPermittedMethodParamAnnotations(permittedInterStageInputAnnotations),permittedInterStageInputAnnotations,ImmutableList.<Class<? extends Annotation>>of());
      PsiAnnotation psiOnTriggerAnnotation=AnnotationUtil.findAnnotation(psiMethod,OnTrigger.class.getName());
      PsiNameValuePair valuePair=AnnotationUtil.findDeclaredAttribute(psiOnTriggerAnnotation,"value");
      PsiClassObjectAccessExpression valueClassExpression=(PsiClassObjectAccessExpression)valuePair.getValue();
      PsiType valueType=valueClassExpression.getOperand().getType();
      PsiClass valueClass=PsiTypesUtil.getPsiClass(valueType);
      final SpecMethodModel<EventMethod,EventDeclarationModel> eventMethod=new SpecMethodModel<EventMethod,EventDeclarationModel>(ImmutableList.<Annotation>of(),PsiProcessingUtils.extractModifiers(psiMethod.getModifierList()),psiMethod.getName(),null,ImmutableList.copyOf(getTypeVariables(psiMethod)),ImmutableList.copyOf(methodParams),psiMethod,new EventDeclarationModel(ClassName.bestGuess(valueClass.getName()),getReturnType(valueClass),getFields(valueClass),valueClass));
      delegateMethods.add(eventMethod);
    }
  }
  return ImmutableList.copyOf(delegateMethods);
}

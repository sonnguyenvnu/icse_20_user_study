private void invoke(@NotNull ProblemsHolder holder,@NotNull PsiElement psiElement){
  if (!(psiElement instanceof StringLiteralExpression) || !(psiElement.getContext() instanceof ParameterList)) {
    return;
  }
  ParameterList parameterList=(ParameterList)psiElement.getContext();
  PsiElement methodReference=parameterList.getContext();
  if (!(methodReference instanceof MethodReference)) {
    return;
  }
  if (!PhpElementsUtil.isMethodReferenceInstanceOf((MethodReference)methodReference,TranslationUtil.PHP_TRANSLATION_SIGNATURES)) {
    return;
  }
  ParameterBag currentIndex=PsiElementUtils.getCurrentParameterIndex(psiElement);
  if (currentIndex == null || currentIndex.getIndex() != 0) {
    return;
  }
  int domainParameter=2;
  if ("transChoice".equals(((MethodReference)methodReference).getName())) {
    domainParameter=3;
  }
  PsiElement domainElement=PsiElementUtils.getMethodParameterPsiElementAt(parameterList,domainParameter);
  if (domainElement == null) {
    annotateTranslationKey((StringLiteralExpression)psiElement,"messages",holder);
  }
 else {
    PsiElement[] parameters=parameterList.getParameters();
    if (parameters.length >= domainParameter) {
      String domain=PhpElementsUtil.getStringValue(parameters[domainParameter]);
      if (domain != null) {
        annotateTranslationKey((StringLiteralExpression)psiElement,domain,holder);
      }
    }
  }
}

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
  int domainParameter=2;
  if ("transChoice".equals(((MethodReference)methodReference).getName())) {
    domainParameter=3;
  }
  ParameterBag currentIndex=PsiElementUtils.getCurrentParameterIndex(psiElement);
  if (currentIndex != null && currentIndex.getIndex() == domainParameter) {
    annotateTranslationDomain((StringLiteralExpression)psiElement,holder);
  }
}

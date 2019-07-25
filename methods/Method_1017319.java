private void invoke(@NotNull final PsiElement psiElement,@NotNull ProblemsHolder holder){
  String className=PsiElementUtils.getText(psiElement);
  if (YamlHelper.isValidParameterName(className)) {
    String resolvedParameter=ContainerCollectionResolver.resolveParameter(psiElement.getProject(),className);
    if (resolvedParameter != null && PhpIndex.getInstance(psiElement.getProject()).getAnyByFQN(resolvedParameter).size() > 0) {
      return;
    }
  }
  PhpClass foundClass=PhpElementsUtil.getClassInterface(psiElement.getProject(),className);
  if (foundClass == null) {
    holder.registerProblem(psiElement,MESSAGE_MISSING_CLASS,ProblemHighlightType.GENERIC_ERROR_OR_WARNING);
  }
 else   if (!foundClass.getPresentableFQN().equals(className)) {
    holder.registerProblem(psiElement,MESSAGE_WRONG_CASING,ProblemHighlightType.GENERIC_ERROR_OR_WARNING,new CorrectClassNameCasingYamlLocalQuickFix(foundClass.getPresentableFQN()));
  }
}

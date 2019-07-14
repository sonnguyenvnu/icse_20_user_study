private static CompletionProvider<CompletionParameters> typeCompletionProvider(){
  return new CompletionProvider<CompletionParameters>(){
    @Override protected void addCompletions(    @NotNull CompletionParameters completionParameters,    ProcessingContext processingContext,    @NotNull CompletionResultSet completionResultSet){
      PsiElement element=completionParameters.getPosition();
      PsiElement typeElement=PsiTreeUtil.getParentOfType(element,PsiTypeElement.class);
      if (typeElement == null) {
        return;
      }
      PsiMethod containingMethod=PsiTreeUtil.getParentOfType(element,PsiMethod.class);
      if (containingMethod == null) {
        return;
      }
      PsiClass cls=containingMethod.getContainingClass();
      if (!LithoPluginUtils.isLithoSpec(cls)) {
        return;
      }
      PsiModifierList parameterModifiers=PsiTreeUtil.getPrevSiblingOfType(typeElement,PsiModifierList.class);
      if (parameterModifiers == null) {
        return;
      }
      if (parameterModifiers.findAnnotation(LithoClassNames.PROP_CLASS_NAME) != null) {
        addCompletionResult(completionResultSet,containingMethod,cls.getMethods(),LithoPluginUtils::isProp);
      }
 else       if (parameterModifiers.findAnnotation(LithoClassNames.STATE_CLASS_NAME) != null) {
        addCompletionResult(completionResultSet,containingMethod,cls.getMethods(),LithoPluginUtils::isState);
      }
    }
  }
;
}

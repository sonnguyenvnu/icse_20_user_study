@Nullable @Override public PsiElement getGotoDeclarationTarget(@Nullable PsiElement sourceElement,Editor editor){
  return BaseLithoComponentsDeclarationHandler.getGotoDeclarationTarget(sourceElement,LithoPluginUtils::isSectionClass,LithoPluginUtils::hasLithoSectionAnnotation,EVENT);
}

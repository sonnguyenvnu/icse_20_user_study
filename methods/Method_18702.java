private static ElementPattern<? extends PsiElement> codeReferencePattern(){
  return PlatformPatterns.psiElement(PsiIdentifier.class).withParent(PsiJavaCodeReferenceElement.class).withLanguage(JavaLanguage.INSTANCE);
}

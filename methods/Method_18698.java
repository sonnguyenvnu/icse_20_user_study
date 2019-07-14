private ElementPattern<? extends PsiElement> annotationInClass(){
  return PlatformPatterns.psiElement(PsiIdentifier.class).withSuperParent(2,PsiAnnotation.class).withSuperParent(4,PsiClass.class).withLanguage(JavaLanguage.INSTANCE);
}

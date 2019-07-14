private ElementPattern<? extends PsiElement> annotationAboveMethod(){
  return PlatformPatterns.psiElement(PsiIdentifier.class).withSuperParent(2,PsiAnnotation.class).withSuperParent(4,PsiMethod.class).withLanguage(JavaLanguage.INSTANCE);
}

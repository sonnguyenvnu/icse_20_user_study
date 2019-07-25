@Override public boolean accepts(@NotNull PsiElement psiElement,ProcessingContext processingContext){
  for (  Class<? extends PsiElement> aClass : classes) {
    psiElement=psiElement.getParent();
    if (psiElement == null || !aClass.isInstance(psiElement)) {
      return false;
    }
  }
  return true;
}

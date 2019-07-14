public static boolean isLithoSpec(@Nullable PsiFile psiFile){
  if (psiFile == null) {
    return false;
  }
  PsiClass psiClass=PsiTreeUtil.findChildOfType(psiFile,PsiClass.class);
  return isLithoSpec(psiClass);
}

public static boolean isLithoSpec(@Nullable PsiClass psiClass){
  return psiClass != null && (hasLithoSectionAnnotation(psiClass) || hasLithoAnnotation(psiClass));
}

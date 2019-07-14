public static boolean hasLithoAnnotation(@Nullable PsiClass psiClass){
  if (psiClass == null) {
    return false;
  }
  return hasAnnotation(psiClass,startsWith("com.facebook.litho.annotations"));
}

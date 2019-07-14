public static boolean hasLithoSectionAnnotation(PsiClass psiClass){
  return hasAnnotation(psiClass,startsWith("com.facebook.litho.sections.annotations"));
}

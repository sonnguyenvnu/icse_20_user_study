public static boolean isEvent(PsiClass psiClass){
  return hasAnnotation(psiClass,equals(LithoClassNames.EVENT_ANNOTATION_NAME));
}

public static boolean isState(PsiParameter parameter){
  return hasAnnotation(parameter,equals(LithoClassNames.STATE_CLASS_NAME));
}

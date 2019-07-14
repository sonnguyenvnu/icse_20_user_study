public static boolean isProp(PsiParameter parameter){
  return hasAnnotation(parameter,equals(LithoClassNames.PROP_CLASS_NAME));
}

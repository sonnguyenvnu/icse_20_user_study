public static boolean isPropDefault(PsiField field){
  return hasAnnotation(field,equals(LithoClassNames.PROP_DEFAULT_CLASS_NAME));
}

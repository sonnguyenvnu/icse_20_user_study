@Override protected boolean validate(@NotNull PsiAnnotation psiAnnotation,@NotNull PsiClass psiClass,@NotNull ProblemBuilder builder){
  boolean result=true;
  if (psiClass.isInterface() || psiClass.isAnnotationType()) {
    builder.addError("@Log is legal only on classes and enums");
    result=false;
  }
  if (result) {
    final String loggerName=getLoggerName(psiClass);
    if (hasFieldByName(psiClass,loggerName)) {
      builder.addError("Not generating field %s: A field with same name already exists",loggerName);
      result=false;
    }
  }
  return result;
}

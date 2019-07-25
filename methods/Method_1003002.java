@Override protected boolean validate(@NotNull PsiAnnotation psiAnnotation,@NotNull PsiMethod psiMethod,@NotNull ProblemBuilder builder){
  boolean result=true;
  if (psiMethod.getParameterList().getParametersCount() > 0) {
    builder.addError("@Delegate is legal only on no-argument methods.");
    result=false;
  }
  final PsiType returnType=psiMethod.getReturnType();
  result&=null != returnType && handler.validate(psiMethod,returnType,psiAnnotation,builder);
  return result;
}

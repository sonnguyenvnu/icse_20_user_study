public boolean validate(@NotNull PsiModifierListOwner psiModifierListOwner,@NotNull PsiType psiType,@NotNull PsiAnnotation psiAnnotation,@NotNull ProblemBuilder builder){
  boolean result=true;
  if (psiModifierListOwner.hasModifierProperty(PsiModifier.STATIC)) {
    builder.addError("@Delegate is legal only on instance fields or no-argument instance methods.");
    result=false;
  }
  final Collection<PsiType> types=collectDelegateTypes(psiAnnotation,psiType);
  result&=validateTypes(types,builder);
  final Collection<PsiType> excludes=collectExcludeTypes(psiAnnotation);
  result&=validateTypes(excludes,builder);
  return result;
}

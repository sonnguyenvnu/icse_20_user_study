@Nullable @Override public ProblemDescriptor[] checkMethod(@NotNull PsiMethod method,@NotNull InspectionManager manager,boolean isOnTheFly){
  if (!LithoPluginUtils.isLithoSpec(method.getContainingClass())) {
    return ProblemDescriptor.EMPTY_ARRAY;
  }
  return Optional.of(method).map(PsiMethod::getParameterList).map(PsiParameterList::getParameters).map(psiParameters -> Stream.of(psiParameters).filter(LithoPluginUtils::isPropOrState).filter(UppercaseStatePropInspection::isFirstLetterCapital).map(PsiParameter::getNameIdentifier).filter(Objects::nonNull).map(identifier -> createWarning(identifier,manager,isOnTheFly)).toArray(ProblemDescriptor[]::new)).orElse(ProblemDescriptor.EMPTY_ARRAY);
}

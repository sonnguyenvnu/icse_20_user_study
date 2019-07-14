static void addCompletionResult(@NotNull CompletionResultSet completionResultSet,PsiMethod currentMethod,PsiMethod[] allMethods,Predicate<PsiParameter> annotationCheck){
  Set<String> excludingParameters=Stream.of(currentMethod.getParameterList().getParameters()).filter(annotationCheck).map(PsiParameter::getName).filter(Objects::nonNull).collect(Collectors.toSet());
  LithoPluginUtils.getPsiParameterStream(currentMethod,allMethods).filter(annotationCheck).filter(parameter -> !excludingParameters.contains(parameter.getName())).map(StatePropCompletionContributor::createCompletionResult).forEach(completionResultSet::addElement);
}

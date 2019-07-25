@Override @NotNull public Collection<SNode> apply(@NotNull TemplateContext context) throws GenerationException {
  if (myRuleConsequence == null) {
    throw new TemplateProcessingFailureException(myRuleNode,"no rule consequence",GeneratorUtil.describeInput(context));
  }
  return myRuleConsequence.processRuleConsequence(context.subContext(myMappingName));
}

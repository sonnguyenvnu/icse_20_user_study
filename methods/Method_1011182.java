public Collection<SNode> apply(@NotNull final TemplateContext context) throws GenerationException {
  return apply(context.getEnvironment(),context);
}

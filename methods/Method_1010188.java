@Nullable @Override public Object evaluate(@NotNull CallArgumentQuery query,@NotNull TemplateArgumentContext context) throws GenerationFailureException {
  try {
    tracer.push(taskName("evaluate template argument query",context.getTemplateReference()));
    return wrapped.evaluate(query,context);
  }
  finally {
    tracer.pop();
  }
}

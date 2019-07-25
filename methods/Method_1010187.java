@Nullable @Override public SNode evaluate(@NotNull InsertMacroQuery query,@NotNull InsertMacroContext context) throws GenerationFailureException {
  try {
    tracer.push(taskName("insert node query",context.getTemplateReference()));
    return wrapped.evaluate(query,context);
  }
  finally {
    tracer.pop();
  }
}

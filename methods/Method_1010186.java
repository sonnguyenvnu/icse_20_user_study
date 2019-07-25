@Override public boolean evaluate(@NotNull InlineSwitchCaseCondition condition,@NotNull InlineSwitchCaseContext context) throws GenerationFailureException {
  try {
    tracer.push(taskName("check condition(with context)",context.getTemplateReference()));
    return wrapped.evaluate(condition,context);
  }
  finally {
    tracer.pop();
  }
}

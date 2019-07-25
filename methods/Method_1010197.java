@Override public boolean evaluate(@NotNull InlineSwitchCaseCondition condition,@NotNull InlineSwitchCaseContext context) throws GenerationFailureException {
  try {
    return condition.check(context);
  }
 catch (  GenerationFailureException ex) {
    throw ex;
  }
catch (  Throwable t) {
    TemplateQueryException ex=new TemplateQueryException("condition of inline switch failed",t);
    ex.setQueryContext(context);
    throw ex;
  }
}

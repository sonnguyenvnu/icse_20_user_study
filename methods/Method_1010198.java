@Override public boolean evaluate(@NotNull IfMacroCondition condition,@NotNull IfMacroContext context) throws GenerationFailureException {
  try {
    return condition.check(context);
  }
 catch (  GenerationFailureException ex) {
    throw ex;
  }
catch (  Throwable t) {
    TemplateQueryException ex=new TemplateQueryException("IF macro condition failed",t);
    ex.setQueryContext(context);
    throw ex;
  }
}

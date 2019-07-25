@Nullable @Override public SNode evaluate(@NotNull InsertMacroQuery query,@NotNull InsertMacroContext context) throws GenerationFailureException {
  try {
    return query.evaluate(context);
  }
 catch (  GenerationFailureException ex) {
    throw ex;
  }
catch (  Throwable t) {
    TemplateQueryException ex=new TemplateQueryException("INSERT macro query failed",t);
    ex.setQueryContext(context);
    throw ex;
  }
}

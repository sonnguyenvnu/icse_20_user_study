@NotNull @Override public Collection<SNode> evaluate(@NotNull SourceNodesQuery query,@NotNull SourceSubstituteMacroNodesContext context) throws GenerationFailureException {
  try {
    return query.evaluate(context);
  }
 catch (  GenerationFailureException ex) {
    throw ex;
  }
catch (  Throwable t) {
    TemplateQueryException ex=new TemplateQueryException("source nodes query failed",t);
    ex.setQueryContext(context);
    throw ex;
  }
}

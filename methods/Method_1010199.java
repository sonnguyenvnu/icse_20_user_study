@Override public void execute(@NotNull MapPostProcessor codeBlock,@NotNull MapSrcMacroPostProcContext context) throws GenerationFailureException {
  try {
    codeBlock.invoke(context);
  }
 catch (  GenerationFailureException ex) {
    throw ex;
  }
catch (  Throwable t) {
    TemplateQueryException ex=new TemplateQueryException("post-processing query failed",t);
    ex.setQueryContext(context);
    throw ex;
  }
}

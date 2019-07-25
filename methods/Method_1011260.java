public Collection<SNode> apply(@NotNull final TemplateExecutionEnvironment environment,@NotNull final TemplateContext context) throws GenerationException {
  final SNode tnode1=environment.createOutputNode(myConcepts[0]);
  try {
    environment.nodeCopied(context,tnode1,"tpl/r:1dfaf07d-c77a-451e-91d3-b6f80f0f8508/7568285956000709565");
    SNodeAccessUtil.setProperty(tnode1,myProperties[0],"generator descriptor");
    TemplateContext context1=context.subContext();
{
      SNode tnode2=null;
      final SNode insertInput2=QueriesGenerated.insertMacro_Query_8_0(new InsertMacroContext(context1,insertMacro_s14ods_b0a0a1a3a1a4));
      if (insertInput2 != null) {
        tnode2=environment.insertNode(insertInput2,insertMacro_s14ods_b0a0a1a3a1a4,context1);
      }
      if (tnode2 != null) {
        tnode1.addChild(myAggregationLinks[0],tnode2);
      }
    }
  }
  finally {
  }
  return TemplateUtil.singletonList(tnode1);
}

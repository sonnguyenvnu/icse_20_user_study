@Override public Collection<SNode> apply(@NotNull TemplateExecutionEnvironment environment,@NotNull TemplateContext context) throws GenerationException {
  final TemplateContainer tc=getTemplates();
  CollectorSink s=new CollectorSink(new ArrayList<>());
  tc.apply(s,context);
  return s.getCollected();
}

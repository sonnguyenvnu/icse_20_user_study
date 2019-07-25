@Override protected void populate(Collection<ReplaceText> replacment){
  replacment.add(new ReplaceText("\r\n","<text:line-break />"));
  replacment.add(new ReplaceText("\n","<text:line-break />"));
  replacment.add(new ReplaceText("\t","<text:tab />"));
}

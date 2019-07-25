@Override public void validate(final Processor<FullData,FullData> processor,final ProcessingReport report,final MessageBundle bundle,final FullData data) throws ProcessingException {
  if (!uniqueItems)   return;
  final Set<Equivalence.Wrapper<JsonNode>> set=Sets.newHashSet();
  final JsonNode node=data.getInstance().getNode();
  for (  final JsonNode element : node)   if (!set.add(EQUIVALENCE.wrap(element))) {
    report.error(newMsg(data,bundle,"err.common.uniqueItems.duplicateElements"));
    return;
  }
}

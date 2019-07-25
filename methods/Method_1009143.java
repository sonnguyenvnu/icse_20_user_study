@Override public void validate(final Processor<FullData,FullData> processor,final ProcessingReport report,final MessageBundle bundle,final FullData data) throws ProcessingException {
  final SchemaTree tree=data.getSchema();
  final JsonPointer schemaPointer=tree.getPointer();
  final JsonNode schemas=tree.getNode().get(keyword);
  final int size=schemas.size();
  final ObjectNode fullReport=FACTORY.objectNode();
  int nrSuccess=0;
  ListProcessingReport subReport;
  JsonPointer ptr;
  FullData newData;
  for (int index=0; index < size; index++) {
    subReport=new ListProcessingReport(report.getLogLevel(),LogLevel.FATAL);
    ptr=schemaPointer.append(JsonPointer.of(keyword,index));
    newData=data.withSchema(tree.setPointer(ptr));
    processor.process(subReport,newData);
    fullReport.put(ptr.toString(),subReport.asJson());
    if (subReport.isSuccess())     nrSuccess++;
  }
  if (nrSuccess != 1)   report.error(newMsg(data,bundle,"err.draftv4.oneOf.fail").putArgument("matched",nrSuccess).putArgument("nrSchemas",size).put("reports",fullReport));
}

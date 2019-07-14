@Override public ObjectNode createParserUIInitializationData(ImportingJob job,List<ObjectNode> fileRecords,String format){
  ObjectNode options=ParsingUtilities.mapper.createObjectNode();
  JSONUtilities.safePut(options,"includeFileSources",fileRecords.size() > 1);
  JSONUtilities.safePut(options,"skipDataLines",0);
  JSONUtilities.safePut(options,"limit",-1);
  return options;
}

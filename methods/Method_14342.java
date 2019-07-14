@Override public ObjectNode createParserUIInitializationData(ImportingJob job,List<ObjectNode> fileRecords,String format){
  ObjectNode options=super.createParserUIInitializationData(job,fileRecords,format);
  if (fileRecords.size() > 0) {
    try {
      ObjectNode firstFileRecord=fileRecords.get(0);
      File file=ImportingUtilities.getFile(job,firstFileRecord);
      JsonFactory factory=new JsonFactory();
      JsonParser parser=factory.createJsonParser(file);
      PreviewParsingState state=new PreviewParsingState();
      JsonNode rootValue=parseForPreview(parser,state);
      if (rootValue != null) {
        JSONUtilities.safePut(options,"dom",rootValue);
      }
    }
 catch (    IOException e) {
      logger.error("Error generating parser UI initialization data for JSON file",e);
    }
  }
  return options;
}

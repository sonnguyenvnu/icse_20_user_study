@Override public ObjectNode createParserUIInitializationData(ImportingJob job,List<ObjectNode> fileRecords,String format){
  ObjectNode options=super.createParserUIInitializationData(job,fileRecords,format);
  try {
    if (fileRecords.size() > 0) {
      ObjectNode firstFileRecord=fileRecords.get(0);
      File file=ImportingUtilities.getFile(job,firstFileRecord);
      InputStream is=new FileInputStream(file);
      try {
        XMLStreamReader parser=createXMLStreamReader(is);
        PreviewParsingState state=new PreviewParsingState();
        while (parser.hasNext() && state.tokenCount < PREVIEW_PARSING_LIMIT) {
          int tokenType=parser.next();
          state.tokenCount++;
          if (tokenType == XMLStreamConstants.START_ELEMENT) {
            ObjectNode rootElement=descendElement(parser,state);
            if (rootElement != null) {
              JSONUtilities.safePut(options,"dom",rootElement);
              break;
            }
          }
 else {
          }
        }
      }
 catch (      XMLStreamException e) {
        logger.warn("Error generating parser UI initialization data for XML file",e);
      }
 finally {
        is.close();
      }
    }
  }
 catch (  IOException e) {
    logger.error("Error generating parser UI initialization data for XML file",e);
  }
  return options;
}

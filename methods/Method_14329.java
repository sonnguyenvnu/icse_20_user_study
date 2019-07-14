@Override public ObjectNode createParserUIInitializationData(ImportingJob job,List<ObjectNode> fileRecords,String format){
  ObjectNode options=super.createParserUIInitializationData(job,fileRecords,format);
  ArrayNode columnWidths=ParsingUtilities.mapper.createArrayNode();
  if (fileRecords.size() > 0) {
    ObjectNode firstFileRecord=fileRecords.get(0);
    String encoding=ImportingUtilities.getEncoding(firstFileRecord);
    String location=JSONUtilities.getString(firstFileRecord,"location",null);
    if (location != null) {
      File file=new File(job.getRawDataDir(),location);
      int[] columnWidthsA=guessColumnWidths(file,encoding);
      if (columnWidthsA != null) {
        for (        int w : columnWidthsA) {
          JSONUtilities.append(columnWidths,w);
        }
      }
    }
    JSONUtilities.safePut(options,"headerLines",0);
    JSONUtilities.safePut(options,"columnWidths",columnWidths);
    JSONUtilities.safePut(options,"guessCellValueTypes",false);
  }
  return options;
}

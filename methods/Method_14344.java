@Override public ObjectNode createParserUIInitializationData(ImportingJob job,List<ObjectNode> fileRecords,String format){
  ObjectNode options=super.createParserUIInitializationData(job,fileRecords,format);
  ArrayNode sheetRecords=ParsingUtilities.mapper.createArrayNode();
  JSONUtilities.safePut(options,"sheetRecords",sheetRecords);
  OdfDocument odfDoc=null;
  try {
    for (int index=0; index < fileRecords.size(); index++) {
      ObjectNode fileRecord=fileRecords.get(index);
      File file=ImportingUtilities.getFile(job,fileRecord);
      InputStream is=new FileInputStream(file);
      odfDoc=OdfDocument.loadDocument(is);
      List<OdfTable> tables=odfDoc.getTableList();
      int sheetCount=tables.size();
      for (int i=0; i < sheetCount; i++) {
        OdfTable sheet=tables.get(i);
        int rows=sheet.getRowCount();
        ObjectNode sheetRecord=ParsingUtilities.mapper.createObjectNode();
        JSONUtilities.safePut(sheetRecord,"name",file.getName() + "#" + sheet.getTableName());
        JSONUtilities.safePut(sheetRecord,"fileNameAndSheetIndex",file.getName() + "#" + i);
        JSONUtilities.safePut(sheetRecord,"rows",rows);
        if (rows > 0) {
          JSONUtilities.safePut(sheetRecord,"selected",true);
        }
 else {
          JSONUtilities.safePut(sheetRecord,"selected",false);
        }
        JSONUtilities.append(sheetRecords,sheetRecord);
      }
    }
  }
 catch (  FileNotFoundException e) {
    logger.info("File not found",e);
  }
catch (  Exception e) {
    logger.info("Error reading ODF spreadsheet",e);
  }
 finally {
    if (odfDoc != null) {
      odfDoc.close();
    }
  }
  return options;
}

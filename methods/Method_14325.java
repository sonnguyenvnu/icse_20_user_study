@Override public ObjectNode createParserUIInitializationData(ImportingJob job,List<ObjectNode> fileRecords,String format){
  ObjectNode options=super.createParserUIInitializationData(job,fileRecords,format);
  ArrayNode sheetRecords=ParsingUtilities.mapper.createArrayNode();
  JSONUtilities.safePut(options,"sheetRecords",sheetRecords);
  try {
    for (int index=0; index < fileRecords.size(); index++) {
      ObjectNode fileRecord=fileRecords.get(index);
      File file=ImportingUtilities.getFile(job,fileRecord);
      InputStream is=new FileInputStream(file);
      if (!is.markSupported()) {
        is=new BufferedInputStream(is);
      }
      try {
        Workbook wb=FileMagic.valueOf(is) == FileMagic.OOXML ? new XSSFWorkbook(is) : new HSSFWorkbook(new POIFSFileSystem(is));
        int sheetCount=wb.getNumberOfSheets();
        for (int i=0; i < sheetCount; i++) {
          Sheet sheet=wb.getSheetAt(i);
          int rows=sheet.getLastRowNum() - sheet.getFirstRowNum() + 1;
          ObjectNode sheetRecord=ParsingUtilities.mapper.createObjectNode();
          JSONUtilities.safePut(sheetRecord,"name",file.getName() + "#" + sheet.getSheetName());
          JSONUtilities.safePut(sheetRecord,"fileNameAndSheetIndex",file.getName() + "#" + i);
          JSONUtilities.safePut(sheetRecord,"rows",rows);
          if (rows > 1) {
            JSONUtilities.safePut(sheetRecord,"selected",true);
          }
 else {
            JSONUtilities.safePut(sheetRecord,"selected",false);
          }
          JSONUtilities.append(sheetRecords,sheetRecord);
        }
        wb.close();
      }
  finally {
        is.close();
      }
    }
  }
 catch (  IOException e) {
    logger.error("Error generating parser UI initialization data for Excel file",e);
  }
catch (  IllegalArgumentException e) {
    logger.error("Error generating parser UI initialization data for Excel file (only Excel 97 & later supported)",e);
  }
catch (  POIXMLException e) {
    logger.error("Error generating parser UI initialization data for Excel file - invalid XML",e);
  }
  return options;
}

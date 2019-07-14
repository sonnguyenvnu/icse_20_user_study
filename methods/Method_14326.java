@Override public void parseOneFile(Project project,ProjectMetadata metadata,ImportingJob job,String fileSource,InputStream inputStream,int limit,ObjectNode options,List<Exception> exceptions){
  Workbook wb=null;
  if (!inputStream.markSupported()) {
    inputStream=new BufferedInputStream(inputStream);
  }
  try {
    wb=FileMagic.valueOf(inputStream) == FileMagic.OOXML ? new XSSFWorkbook(inputStream) : new HSSFWorkbook(new POIFSFileSystem(inputStream));
  }
 catch (  IOException e) {
    exceptions.add(new ImportException("Attempted to parse as an Excel file but failed. " + "Try to use Excel to re-save the file as a different Excel version or as TSV and upload again.",e));
    return;
  }
catch (  ArrayIndexOutOfBoundsException e) {
    exceptions.add(new ImportException("Attempted to parse file as an Excel file but failed. " + "This is probably caused by a corrupt excel file, or due to the file having previously been created or saved by a non-Microsoft application. " + "Please try opening the file in Microsoft Excel and resaving it, then try re-uploading the file. " + "See https://issues.apache.org/bugzilla/show_bug.cgi?id=48261 for further details",e));
    return;
  }
catch (  IllegalArgumentException e) {
    exceptions.add(new ImportException("Attempted to parse as an Excel file but failed. " + "Only Excel 97 and later formats are supported.",e));
    return;
  }
catch (  POIXMLException e) {
    exceptions.add(new ImportException("Attempted to parse as an Excel file but failed. " + "Invalid XML.",e));
    return;
  }
  ArrayNode sheets=(ArrayNode)options.get("sheets");
  for (int i=0; i < sheets.size(); i++) {
    String[] fileNameAndSheetIndex=new String[2];
    ObjectNode sheetObj=(ObjectNode)sheets.get(i);
    fileNameAndSheetIndex=sheetObj.get("fileNameAndSheetIndex").asText().split("#");
    if (!fileNameAndSheetIndex[0].equals(fileSource))     continue;
    final Sheet sheet=wb.getSheetAt(Integer.parseInt(fileNameAndSheetIndex[1]));
    final int lastRow=sheet.getLastRowNum();
    TableDataReader dataReader=new TableDataReader(){
      @Override public List<Object> getNextRowOfCells() throws IOException {
        if (nextRow > lastRow) {
          return null;
        }
        List<Object> cells=new ArrayList<Object>();
        org.apache.poi.ss.usermodel.Row row=sheet.getRow(nextRow++);
        if (row != null) {
          short lastCell=row.getLastCellNum();
          for (short cellIndex=0; cellIndex < lastCell; cellIndex++) {
            Cell cell=null;
            org.apache.poi.ss.usermodel.Cell sourceCell=row.getCell(cellIndex);
            if (sourceCell != null) {
              cell=extractCell(sourceCell,reconMap);
            }
            cells.add(cell);
          }
        }
        return cells;
      }
    }
;
    TabularImportingParserBase.readTable(project,metadata,job,dataReader,fileSource + "#" + sheet.getSheetName(),limit,options,exceptions);
  }
  super.parseOneFile(project,metadata,job,fileSource,inputStream,limit,options,exceptions);
}

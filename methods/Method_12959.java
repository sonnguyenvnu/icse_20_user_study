public static Workbook createWorkBook(InputStream templateInputStream,ExcelTypeEnum excelType) throws IOException {
  Workbook workbook;
  if (ExcelTypeEnum.XLS.equals(excelType)) {
    workbook=(templateInputStream == null) ? new HSSFWorkbook() : new HSSFWorkbook(new POIFSFileSystem(templateInputStream));
  }
 else {
    workbook=(templateInputStream == null) ? new SXSSFWorkbook(500) : new SXSSFWorkbook(new XSSFWorkbook(templateInputStream));
  }
  return workbook;
}

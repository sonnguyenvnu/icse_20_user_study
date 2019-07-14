@Override protected void execute(){
  Sheet sheetParam=analysisContext.getCurrentSheet();
  if (sheetParam != null && sheetParam.getSheetNo() > 0 && sheetSourceList.size() >= sheetParam.getSheetNo()) {
    InputStream sheetInputStream=sheetSourceList.get(sheetParam.getSheetNo() - 1).getInputStream();
    parseXmlSource(sheetInputStream);
  }
 else {
    int i=0;
    for (    SheetSource sheetSource : sheetSourceList) {
      i++;
      analysisContext.setCurrentSheet(new Sheet(i));
      parseXmlSource(sheetSource.getInputStream());
    }
  }
}

@Override public void setCurrentSheet(Sheet currentSheet){
  cleanCurrentSheet();
  this.currentSheet=currentSheet;
  if (currentSheet.getClazz() != null) {
    buildExcelHeadProperty(currentSheet.getClazz(),null);
  }
}

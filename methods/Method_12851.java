private void init(){
  lastRowNumber=0;
  lastColumnNumber=0;
  nextRow=0;
  nextColumn=0;
  sheetIndex=0;
  records=new ArrayList<String>();
  notAllEmpty=false;
  orderedBSRs=null;
  boundSheetRecords=new ArrayList<BoundSheetRecord>();
  sheets=new ArrayList<Sheet>();
  if (analysisContext.getCurrentSheet() == null) {
    this.analyAllSheet=true;
  }
 else {
    this.analyAllSheet=false;
  }
}

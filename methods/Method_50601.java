@Override void calculateLineNumbers(SourceCodePositioner positioner){
  super.calculateLineNumbers(positioner);
  this.endLine=positioner.getLastLine();
  this.endColumn=positioner.getLastLineColumn();
}

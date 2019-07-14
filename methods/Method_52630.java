void calculateLineNumbers(SourceCodePositioner positioner){
  int startOffset=node.getAbsolutePosition();
  int endOffset=startOffset + node.getLength();
  this.beginLine=positioner.lineNumberFromOffset(startOffset);
  this.beginColumn=positioner.columnFromOffset(this.beginLine,startOffset);
  this.endLine=positioner.lineNumberFromOffset(endOffset);
  this.endColumn=positioner.columnFromOffset(this.endLine,endOffset) - 1;
  if (this.endColumn < 0) {
    this.endColumn=0;
  }
}

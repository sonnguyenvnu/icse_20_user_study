void calculateLineNumbers(SourceCodePositioner positioner,int startOffset,int endOffset){
  endOffset-=1;
  this.beginLine=positioner.lineNumberFromOffset(startOffset);
  this.beginColumn=positioner.columnFromOffset(this.beginLine,startOffset);
  this.endLine=positioner.lineNumberFromOffset(endOffset);
  this.endColumn=positioner.columnFromOffset(this.endLine,endOffset);
  if (this.endColumn < 0) {
    this.endColumn=0;
  }
}

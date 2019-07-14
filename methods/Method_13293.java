protected void setMaxLineNumber(int maxLineNumber){
  if (maxLineNumber > 0) {
    if (lineNumberMap == null) {
      lineNumberMap=new int[maxLineNumber + 1];
    }
 else     if (lineNumberMap.length <= maxLineNumber) {
      int[] tmp=new int[maxLineNumber + 1];
      System.arraycopy(lineNumberMap,0,tmp,0,lineNumberMap.length);
      lineNumberMap=tmp;
    }
    this.maxLineNumber=maxLineNumber;
  }
}

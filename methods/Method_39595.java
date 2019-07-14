/** 
 * Adds a source line number corresponding to this label.
 * @param lineNumber a source line number (which should be strictly positive).
 */
final void addLineNumber(final int lineNumber){
  if (this.lineNumber == 0) {
    this.lineNumber=(short)lineNumber;
  }
 else {
    if (otherLineNumbers == null) {
      otherLineNumbers=new int[LINE_NUMBERS_CAPACITY_INCREMENT];
    }
    int otherLineNumberIndex=++otherLineNumbers[0];
    if (otherLineNumberIndex >= otherLineNumbers.length) {
      int[] newLineNumbers=new int[otherLineNumbers.length + LINE_NUMBERS_CAPACITY_INCREMENT];
      System.arraycopy(otherLineNumbers,0,newLineNumbers,0,otherLineNumbers.length);
      otherLineNumbers=newLineNumbers;
    }
    otherLineNumbers[otherLineNumberIndex]=lineNumber;
  }
}

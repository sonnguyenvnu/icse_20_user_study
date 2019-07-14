/** 
 * Informs the token marker that lines have been inserted into the document. This inserts a gap in the <code>lineInfo</code> array.
 * @param index The first line number
 * @param lines The number of lines
 */
public void insertLines(int index,int lines){
  if (lines <= 0)   return;
  length+=lines;
  ensureCapacity(length);
  int len=index + lines;
  System.arraycopy(lineInfo,index,lineInfo,len,lineInfo.length - len);
  for (int i=index + lines - 1; i >= index; i--) {
    lineInfo[i]=Token.NULL;
  }
}

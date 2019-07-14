/** 
 * Informs the token marker that line have been deleted from the document. This removes the lines in question from the <code>lineInfo</code> array.
 * @param index The first line number
 * @param lines The number of lines
 */
public void deleteLines(int index,int lines){
  if (lines <= 0)   return;
  int len=index + lines;
  length-=lines;
  System.arraycopy(lineInfo,len,lineInfo,index,lineInfo.length - len);
}

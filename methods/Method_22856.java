/** 
 * Ensures that the <code>lineInfo</code> array can contain the specified index. This enlarges it if necessary. No action is taken if the array is large enough already.<p> <p> It should be unnecessary to call this under normal circumstances; <code>insertLine()</code> should take care of enlarging the line info array automatically.
 * @param index The array index
 */
protected void ensureCapacity(int index){
  if (lineInfo == null) {
    lineInfo=new byte[index + 1];
  }
 else   if (lineInfo.length <= index) {
    byte[] lineInfoN=new byte[(index + 1) * 2];
    System.arraycopy(lineInfo,0,lineInfoN,0,lineInfo.length);
    lineInfo=lineInfoN;
  }
}

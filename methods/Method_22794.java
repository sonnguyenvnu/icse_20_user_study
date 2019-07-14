/** 
 * Copies the text on the specified line into a segment. If the line is invalid, the segment will contain a null string.
 * @param lineIndex The line
 */
public final void getLineText(int lineIndex,Segment segment){
  int start=getLineStartOffset(lineIndex);
  getText(start,getLineStopOffset(lineIndex) - start - 1,segment);
}

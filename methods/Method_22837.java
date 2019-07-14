/** 
 * Reparses the document, by passing the specified lines to the token marker. This should be called after a large quantity of text is first inserted.
 * @param start The first line to parse
 * @param len The number of lines, after the first one to parse
 */
public void tokenizeLines(int start,int len){
  if (tokenMarker == null || !tokenMarker.marker.supportsMultilineTokens())   return;
  Segment lineSegment=new Segment();
  Element map=getDefaultRootElement();
  len+=start;
  try {
    for (int i=start; i < len; i++) {
      Element lineElement=map.getElement(i);
      int lineStart=lineElement.getStartOffset();
      getText(lineStart,lineElement.getEndOffset() - lineStart - 1,lineSegment);
      tokenMarker.markTokens(lineSegment,i);
    }
  }
 catch (  BadLocationException bl) {
    bl.printStackTrace();
  }
}

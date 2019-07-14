/** 
 * Converts an offset in a line into an x coordinate. This is a fast version that should only be used if no changes were made to the text since the last repaint.
 * @param line The line
 * @param offset The offset, from the start of the line
 */
public int _offsetToX(int line,int offset){
  TokenMarkerState tokenMarker=getTokenMarker();
  FontMetrics fm=painter.getFontMetrics();
  getLineText(line,lineSegment);
  int segmentOffset=lineSegment.offset;
  int x=horizontalOffset;
  if (tokenMarker == null) {
    lineSegment.count=offset;
    return x + Utilities.getTabbedTextWidth(lineSegment,fm,x,painter,0);
  }
 else {
    Token tokens;
    if (painter.currentLineIndex == line && painter.currentLineTokens != null) {
      tokens=painter.currentLineTokens;
    }
 else {
      painter.currentLineIndex=line;
      tokens=painter.currentLineTokens=tokenMarker.markTokens(lineSegment,line);
    }
    SyntaxStyle[] styles=painter.getStyles();
    for (; ; ) {
      byte id=tokens.id;
      if (id == Token.END) {
        return x;
      }
      if (id == Token.NULL) {
        fm=painter.getFontMetrics();
      }
 else {
        fm=painter.getFontMetrics(styles[id]);
      }
      int length=tokens.length;
      if (offset + segmentOffset < lineSegment.offset + length) {
        lineSegment.count=offset - (lineSegment.offset - segmentOffset);
        return x + Utilities.getTabbedTextWidth(lineSegment,fm,x,painter,0);
      }
 else {
        lineSegment.count=length;
        x+=Utilities.getTabbedTextWidth(lineSegment,fm,x,painter,0);
        lineSegment.offset+=length;
      }
      tokens=tokens.next;
    }
  }
}

/** 
 * Converts an x co-ordinate to an offset within a line.
 * @param line The line
 * @param x The x co-ordinate
 */
public int xToOffset(int line,int x){
  TokenMarkerState tokenMarker=getTokenMarker();
  FontMetrics fm=painter.getFontMetrics();
  getLineText(line,lineSegment);
  char[] segmentArray=lineSegment.array;
  int segmentOffset=lineSegment.offset;
  int segmentCount=lineSegment.count;
  int width=horizontalOffset;
  if (tokenMarker == null) {
    for (int i=0; i < segmentCount; i++) {
      char c=segmentArray[i + segmentOffset];
      int charWidth;
      if (c == '\t')       charWidth=(int)painter.nextTabStop(width,i) - width;
 else       charWidth=fm.charWidth(c);
      if (painter.isBlockCaretEnabled()) {
        if (x - charWidth <= width)         return i;
      }
 else {
        if (x - charWidth / 2 <= width)         return i;
      }
      width+=charWidth;
    }
    return segmentCount;
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
    int offset=0;
    SyntaxStyle[] styles=painter.getStyles();
    for (; ; ) {
      byte id=tokens.id;
      if (id == Token.END)       return offset;
      if (id == Token.NULL) {
        fm=painter.getFontMetrics();
      }
 else {
        fm=painter.getFontMetrics(styles[id]);
      }
      int length=tokens.length;
      for (int i=0; i < length; i++) {
        if (segmentOffset + offset + i >= segmentArray.length) {
          return segmentArray.length - segmentOffset - 1;
        }
        char c=segmentArray[segmentOffset + offset + i];
        int charWidth;
        if (c == '\t') {
          charWidth=(int)painter.nextTabStop(width,offset + i) - width;
        }
 else {
          charWidth=fm.charWidth(c);
        }
        if (painter.isBlockCaretEnabled()) {
          if (x - charWidth <= width) {
            return offset + i;
          }
        }
 else {
          if (x - charWidth / 2 <= width) {
            return offset + i;
          }
        }
        width+=charWidth;
      }
      offset+=length;
      tokens=tokens.next;
    }
  }
}

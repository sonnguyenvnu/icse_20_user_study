private void emitAsHTML(StringBuilder cf,int line,SyntaxDocument doc){
  Segment segment=new Segment();
  try {
    Element element=doc.getDefaultRootElement().getElement(line);
    int start=element.getStartOffset();
    int stop=element.getEndOffset();
    doc.getText(start,stop - start - 1,segment);
  }
 catch (  BadLocationException e) {
    return;
  }
  char[] segmentArray=segment.array;
  int limit=segment.getEndIndex();
  int segmentOffset=segment.offset;
  int segmentCount=segment.count;
  TokenMarkerState tokenMarker=doc.getTokenMarker();
  if (tokenMarker == null) {
    for (int j=0; j < segmentCount; j++) {
      char c=segmentArray[j + segmentOffset];
      appendAsHTML(cf,c);
    }
  }
 else {
    Token tokens=tokenMarker.markTokens(segment,line);
    int offset=0;
    SyntaxStyle[] styles=painter.getStyles();
    for (; ; ) {
      byte id=tokens.id;
      if (id == Token.END) {
        if (segmentOffset + offset < limit) {
          appendAsHTML(cf,segmentArray[segmentOffset + offset]);
        }
 else {
          cf.append('\n');
        }
        return;
      }
      if (id != Token.NULL) {
        cf.append("<span style=\"color: #");
        cf.append(PApplet.hex(styles[id].getColor().getRGB() & 0xFFFFFF,6));
        cf.append(";\">");
        if (styles[id].isBold())         cf.append("<b>");
      }
      int length=tokens.length;
      for (int j=0; j < length; j++) {
        char c=segmentArray[segmentOffset + offset + j];
        if (offset == 0 && c == ' ') {
          cf.append("&nbsp;");
        }
 else {
          appendAsHTML(cf,c);
        }
        if (j == (length - 1) && id != Token.NULL && styles[id].isBold())         cf.append("</b>");
        if (j == (length - 1) && id != Token.NULL)         cf.append("</span>");
      }
      offset+=length;
      tokens=tokens.next;
    }
  }
}

private void deal(int startOffset,int endOffset){
  try {
    String text=doc.getText(startOffset,endOffset - startOffset);
    if (text == null || "".equals(text)) {
      return;
    }
    if (text.trim().startsWith("/*") && text.trim().endsWith("*/")) {
      doc.setCharacterAttributes(startOffset,endOffset - startOffset,commentAttr,true);
    }
 else {
      int pos=text.indexOf(":");
      if (pos >= 0) {
        if (pos > 0) {
          doc.setCharacterAttributes(startOffset,pos,labelAttr,true);
        }
        if (pos + 1 < endOffset) {
          doc.setCharacterAttributes(startOffset + pos + 1,endOffset - startOffset - pos - 1,valueAttr,true);
        }
      }
      int p1=text.indexOf("/*");
      if (p1 >= 0) {
        int p2=text.indexOf("*/");
        if (p2 >= 0) {
          doc.setCharacterAttributes(startOffset + p1,p2 - p1 + 2,commentAttr,true);
        }
      }
    }
  }
 catch (  BadLocationException e) {
    e.printStackTrace();
  }
}

/** 
 * If necessary, wrap the text into multiple lines.
 * @param lines line array in which to store the wrapped lines
 * @param elem  the document element containing the text content
 */
protected void wrap(final List<String> lines,final Element elem){
  final int p1=elem.getEndOffset();
  final Document doc=elem.getDocument();
  for (int p0=elem.getStartOffset(); p0 < p1; ) {
    final int p=calculateBreakPosition(doc,p0,p1);
    try {
      lines.add(doc.getText(p0,p - p0));
    }
 catch (    BadLocationException e) {
      throw new Error("Can't get line text. p0=" + p0 + " p=" + p);
    }
    p0=(p == p0) ? p1 : p;
  }
}

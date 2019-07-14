protected void parseLine(String content,int index,int eol){
  int start=content.indexOf("at ",index);
  if ((start != -1) && (start < eol)) {
    int leftParenthesisIndex=content.indexOf('(',start);
    if ((leftParenthesisIndex != -1) && (leftParenthesisIndex < eol)) {
      addHyperlink(new LogHyperlinkData(start + 3,leftParenthesisIndex));
    }
  }
}

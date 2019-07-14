@Override public void jjtOpen(){
  first=parser.getToken(1);
  if (beginLine == -1 && parser.token.next != null) {
    beginLine=parser.token.next.beginLine;
    beginColumn=parser.token.next.beginColumn;
  }
}

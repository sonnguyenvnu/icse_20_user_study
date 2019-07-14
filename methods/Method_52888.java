@Override public void jjtClose(){
  last=parser.getToken(0);
  if (beginLine == -1 && (children == null || children.length == 0)) {
    beginColumn=parser.token.beginColumn;
  }
  if (beginLine == -1) {
    beginLine=parser.token.beginLine;
  }
  endLine=parser.token.endLine;
  endColumn=parser.token.endColumn;
}

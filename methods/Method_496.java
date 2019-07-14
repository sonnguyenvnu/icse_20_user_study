public final void nextIdent(){
  while (isWhitespace(ch)) {
    next();
  }
  if (ch == '_' || ch == '$' || Character.isLetter(ch)) {
    scanIdent();
  }
 else {
    nextToken();
  }
}

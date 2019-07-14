public int getLineCount(TokenEntry mark,Match match){
  TokenEntry endTok=get(mark.getIndex() + match.getTokenCount() - 1);
  if (endTok == TokenEntry.EOF) {
    endTok=get(mark.getIndex() + match.getTokenCount() - 2);
  }
  return endTok.getBeginLine() - mark.getBeginLine() + 1;
}

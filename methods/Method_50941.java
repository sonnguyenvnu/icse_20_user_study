@Override public int getEndColumn(){
  return token.getCharPositionInLine() + token.getStopIndex() - token.getStartIndex();
}

@Override public Object getNextToken(){
  AntlrToken nextToken=getNextTokenFromAnyChannel();
  while (!nextToken.isDefault()) {
    nextToken=getNextTokenFromAnyChannel();
  }
  return nextToken;
}

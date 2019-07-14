@Override protected boolean shouldStopProcessing(final AntlrToken currentToken){
  return currentToken.getType() == EOF;
}

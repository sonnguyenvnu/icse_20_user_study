@Override protected boolean shouldStopProcessing(final GenericToken currentToken){
  return currentToken.getImage().isEmpty();
}

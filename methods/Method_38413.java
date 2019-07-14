@Override protected boolean bufferStatusCode(final int statusCode){
  return decoraManager.decorateStatusCode(statusCode);
}

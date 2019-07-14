public boolean needsRefetch(){
  return ((System.currentTimeMillis() - timeFetched) > EXPIRATION_DELAY);
}

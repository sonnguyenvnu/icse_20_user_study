@Override public void clear(){
  try {
    for (    String s : fastStorage.findTokens()) {
      fastStorage.removeToken(s);
    }
  }
 catch (  FastStorageException e) {
    throw new IllegalStateException(e);
  }
}

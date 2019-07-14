@Override public void add(String token){
  try {
    fastStorage.saveToken(token);
  }
 catch (  FastStorageException e) {
    throw new IllegalStateException(e);
  }
}

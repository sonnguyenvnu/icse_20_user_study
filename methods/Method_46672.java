@Override public boolean exist(String token){
  try {
    List<String> tokens=fastStorage.findTokens();
    return tokens.contains(token);
  }
 catch (  FastStorageException e) {
    return false;
  }
}

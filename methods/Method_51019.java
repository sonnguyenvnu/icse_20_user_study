@Override public TokenManager getTokenManager(String fileName,Reader source){
  TokenManager tokenManager=createTokenManager(source);
  tokenManager.setFileName(fileName);
  return tokenManager;
}

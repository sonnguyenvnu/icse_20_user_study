private String geToken(){
  UserToken token=UserTokenHolder.currentToken();
  return null != token ? token.getToken() : "";
}

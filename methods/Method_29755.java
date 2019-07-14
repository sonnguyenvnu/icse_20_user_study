public void start(String authTokenType,String username,String password){
  start(new RequestState(authTokenType,username,password));
}

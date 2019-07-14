@Override public void changeUserState(String user,TokenState state){
  getByUserId(user).forEach(token -> changeTokenState(token.getToken(),state));
}

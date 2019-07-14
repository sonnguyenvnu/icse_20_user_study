@Override public void changeTokenState(String token,TokenState state){
  changeTokenState(getByToken(token),state);
}

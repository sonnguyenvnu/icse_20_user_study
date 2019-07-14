public void changeTokenState(SimpleUserToken userToken,TokenState state){
  if (null != userToken) {
    SimpleUserToken copy=userToken.copy();
    userToken.setState(state);
    syncToken(userToken);
    publishEvent(new UserTokenChangedEvent(copy,userToken));
  }
}

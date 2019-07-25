@Override public void handle(LoginSuccess loginSuccess) throws Exception {
  Preconditions.checkState(thisState == State.LOGIN_SUCCESS,"Not expecting LOGIN_SUCCESS");
  ch.setProtocol(Protocol.GAME);
  thisState=State.LOGIN;
  if (user.getServer() != null && user.getForgeClientHandler().isHandshakeComplete() && user.getServer().isForgeServer()) {
    user.getForgeClientHandler().resetHandshake();
  }
  throw CancelSendSignal.INSTANCE;
}

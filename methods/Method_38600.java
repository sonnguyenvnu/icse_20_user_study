@Override public JoyDb disableAutoConfiguration(){
  requireNotStarted(connectionProvider);
  autoConfiguration=false;
  return this;
}

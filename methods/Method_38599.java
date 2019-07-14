@Override public JoyDb disableDatabase(){
  requireNotStarted(connectionProvider);
  databaseEnabled=false;
  return this;
}

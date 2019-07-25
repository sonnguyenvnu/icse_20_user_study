@Override public void handle(KeepAlive alive) throws Exception {
  if (alive.getRandomId() == con.getServer().getSentPingId()) {
    int newPing=(int)(System.currentTimeMillis() - con.getSentPingTime());
    con.getTabListHandler().onPingChange(newPing);
    con.setPing(newPing);
  }
 else {
    throw CancelSendSignal.INSTANCE;
  }
}

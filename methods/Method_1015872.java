@Override public void handle(KeepAlive alive) throws Exception {
  server.setSentPingId(alive.getRandomId());
  con.setSentPingTime(System.currentTimeMillis());
}

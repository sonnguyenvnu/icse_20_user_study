@Override public void exception(Throwable t) throws Exception {
  if (server.isObsolete()) {
    return;
  }
  ServerInfo def=con.updateAndGetNextServer(server.getInfo());
  if (def != null) {
    server.setObsolete(true);
    con.connectNow(def,ServerConnectEvent.Reason.SERVER_DOWN_REDIRECT);
    con.sendMessage(bungee.getTranslation("server_went_down"));
  }
 else {
    con.disconnect(Util.exception(t));
  }
}

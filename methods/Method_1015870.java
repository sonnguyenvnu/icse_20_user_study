@Override public void disconnected(ChannelWrapper channel) throws Exception {
  server.getInfo().removePlayer(con);
  if (bungee.getReconnectHandler() != null) {
    bungee.getReconnectHandler().setServer(con);
  }
  if (!server.isObsolete()) {
    con.disconnect(bungee.getTranslation("lost_connection"));
  }
  ServerDisconnectEvent serverDisconnectEvent=new ServerDisconnectEvent(con,server.getInfo());
  bungee.getPluginManager().callEvent(serverDisconnectEvent);
}

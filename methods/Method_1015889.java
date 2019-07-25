@Override public void disconnected(ChannelWrapper channel) throws Exception {
  PlayerDisconnectEvent event=new PlayerDisconnectEvent(con);
  bungee.getPluginManager().callEvent(event);
  con.getTabListHandler().onDisconnect();
  BungeeCord.getInstance().removeConnection(con);
  if (con.getServer() != null) {
    PlayerListItem packet=new PlayerListItem();
    packet.setAction(PlayerListItem.Action.REMOVE_PLAYER);
    PlayerListItem.Item item=new PlayerListItem.Item();
    item.setUuid(con.getUniqueId());
    packet.setItems(new PlayerListItem.Item[]{item});
    for (    ProxiedPlayer player : con.getServer().getInfo().getPlayers()) {
      player.unsafe().sendPacket(packet);
    }
    con.getServer().disconnect("Quitting");
  }
}

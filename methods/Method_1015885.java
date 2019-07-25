private void finish(){
  if (isOnlineMode()) {
    ProxiedPlayer oldName=bungee.getPlayer(getName());
    if (oldName != null) {
      oldName.disconnect(bungee.getTranslation("already_connected_proxy"));
    }
    ProxiedPlayer oldID=bungee.getPlayer(getUniqueId());
    if (oldID != null) {
      oldID.disconnect(bungee.getTranslation("already_connected_proxy"));
    }
  }
 else {
    ProxiedPlayer oldName=bungee.getPlayer(getName());
    if (oldName != null) {
      disconnect(bungee.getTranslation("already_connected_proxy"));
      return;
    }
  }
  offlineId=UUID.nameUUIDFromBytes(("OfflinePlayer:" + getName()).getBytes(Charsets.UTF_8));
  if (uniqueId == null) {
    uniqueId=offlineId;
  }
  Callback<LoginEvent> complete=new Callback<LoginEvent>(){
    @Override public void done(    LoginEvent result,    Throwable error){
      if (result.isCancelled()) {
        disconnect(result.getCancelReasonComponents());
        return;
      }
      if (ch.isClosed()) {
        return;
      }
      ch.getHandle().eventLoop().execute(new Runnable(){
        @Override public void run(){
          if (!ch.isClosing()) {
            UserConnection userCon=new UserConnection(bungee,ch,getName(),InitialHandler.this);
            userCon.setCompressionThreshold(BungeeCord.getInstance().config.getCompressionThreshold());
            userCon.init();
            unsafe.sendPacket(new LoginSuccess(getUniqueId().toString(),getName()));
            ch.setProtocol(Protocol.GAME);
            ch.getHandle().pipeline().get(HandlerBoss.class).setHandler(new UpstreamBridge(bungee,userCon));
            bungee.getPluginManager().callEvent(new PostLoginEvent(userCon));
            ServerInfo server;
            if (bungee.getReconnectHandler() != null) {
              server=bungee.getReconnectHandler().getServer(userCon);
            }
 else {
              server=AbstractReconnectHandler.getForcedHost(InitialHandler.this);
            }
            if (server == null) {
              server=bungee.getServerInfo(listener.getDefaultServer());
            }
            userCon.connect(server,null,true,ServerConnectEvent.Reason.JOIN_PROXY);
            thisState=State.FINISHED;
          }
        }
      }
);
    }
  }
;
  bungee.getPluginManager().callEvent(new LoginEvent(InitialHandler.this,complete));
}

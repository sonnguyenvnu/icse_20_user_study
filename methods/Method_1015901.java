@Override public void connected(ChannelWrapper channel) throws Exception {
  this.ch=channel;
  this.handshakeHandler=new ForgeServerHandler(user,ch,target);
  Handshake originalHandshake=user.getPendingConnection().getHandshake();
  Handshake copiedHandshake=new Handshake(originalHandshake.getProtocolVersion(),originalHandshake.getHost(),originalHandshake.getPort(),2);
  if (BungeeCord.getInstance().config.isIpForward()) {
    String newHost=copiedHandshake.getHost() + "\00" + user.getAddress().getHostString() + "\00" + user.getUUID();
    LoginResult profile=user.getPendingConnection().getLoginProfile();
    if (profile != null && profile.getProperties() != null && profile.getProperties().length > 0) {
      newHost+="\00" + BungeeCord.getInstance().gson.toJson(profile.getProperties());
    }
    copiedHandshake.setHost(newHost);
  }
 else   if (!user.getExtraDataInHandshake().isEmpty()) {
    copiedHandshake.setHost(copiedHandshake.getHost() + user.getExtraDataInHandshake());
  }
  channel.write(copiedHandshake);
  channel.setProtocol(Protocol.LOGIN);
  channel.write(new LoginRequest(user.getName()));
}

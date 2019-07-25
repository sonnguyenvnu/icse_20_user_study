@Override public void handle(PluginMessage pluginMessage) throws Exception {
  DataInput in=pluginMessage.getStream();
  PluginMessageEvent event=new PluginMessageEvent(server,con,pluginMessage.getTag(),pluginMessage.getData().clone());
  if (bungee.getPluginManager().callEvent(event).isCancelled()) {
    throw CancelSendSignal.INSTANCE;
  }
  if (pluginMessage.getTag().equals(con.getPendingConnection().getVersion() >= ProtocolConstants.MINECRAFT_1_13 ? "minecraft:brand" : "MC|Brand")) {
    ByteBuf brand=Unpooled.wrappedBuffer(pluginMessage.getData());
    String serverBrand=DefinedPacket.readString(brand);
    brand.release();
    Preconditions.checkState(!serverBrand.contains(bungee.getName()),"Cannot connect proxy to itself!");
    brand=ByteBufAllocator.DEFAULT.heapBuffer();
    DefinedPacket.writeString(bungee.getName() + " (" + bungee.getVersion() + ")" + " <- " + serverBrand,brand);
    pluginMessage.setData(DefinedPacket.toArray(brand));
    brand.release();
    con.unsafe().sendPacket(pluginMessage);
    throw CancelSendSignal.INSTANCE;
  }
  if (pluginMessage.getTag().equals("BungeeCord")) {
    ByteArrayDataOutput out=ByteStreams.newDataOutput();
    String subChannel=in.readUTF();
    if (subChannel.equals("ForwardToPlayer")) {
      ProxiedPlayer target=bungee.getPlayer(in.readUTF());
      if (target != null) {
        String channel=in.readUTF();
        short len=in.readShort();
        byte[] data=new byte[len];
        in.readFully(data);
        out.writeUTF(channel);
        out.writeShort(data.length);
        out.write(data);
        byte[] payload=out.toByteArray();
        target.getServer().sendData("BungeeCord",payload);
      }
      out=null;
    }
    if (subChannel.equals("Forward")) {
      String target=in.readUTF();
      String channel=in.readUTF();
      short len=in.readShort();
      byte[] data=new byte[len];
      in.readFully(data);
      out.writeUTF(channel);
      out.writeShort(data.length);
      out.write(data);
      byte[] payload=out.toByteArray();
      out=null;
      if (target.equals("ALL")) {
        for (        ServerInfo server : bungee.getServers().values()) {
          if (server != this.server.getInfo()) {
            server.sendData("BungeeCord",payload);
          }
        }
      }
 else       if (target.equals("ONLINE")) {
        for (        ServerInfo server : bungee.getServers().values()) {
          if (server != this.server.getInfo()) {
            server.sendData("BungeeCord",payload,false);
          }
        }
      }
 else {
        ServerInfo server=bungee.getServerInfo(target);
        if (server != null) {
          server.sendData("BungeeCord",payload);
        }
      }
    }
    if (subChannel.equals("Connect")) {
      ServerInfo server=bungee.getServerInfo(in.readUTF());
      if (server != null) {
        con.connect(server,ServerConnectEvent.Reason.PLUGIN_MESSAGE);
      }
    }
    if (subChannel.equals("ConnectOther")) {
      ProxiedPlayer player=bungee.getPlayer(in.readUTF());
      if (player != null) {
        ServerInfo server=bungee.getServerInfo(in.readUTF());
        if (server != null) {
          player.connect(server);
        }
      }
    }
    if (subChannel.equals("IP")) {
      out.writeUTF("IP");
      out.writeUTF(con.getAddress().getHostString());
      out.writeInt(con.getAddress().getPort());
    }
    if (subChannel.equals("PlayerCount")) {
      String target=in.readUTF();
      out.writeUTF("PlayerCount");
      if (target.equals("ALL")) {
        out.writeUTF("ALL");
        out.writeInt(bungee.getOnlineCount());
      }
 else {
        ServerInfo server=bungee.getServerInfo(target);
        if (server != null) {
          out.writeUTF(server.getName());
          out.writeInt(server.getPlayers().size());
        }
      }
    }
    if (subChannel.equals("PlayerList")) {
      String target=in.readUTF();
      out.writeUTF("PlayerList");
      if (target.equals("ALL")) {
        out.writeUTF("ALL");
        out.writeUTF(Util.csv(bungee.getPlayers()));
      }
 else {
        ServerInfo server=bungee.getServerInfo(target);
        if (server != null) {
          out.writeUTF(server.getName());
          out.writeUTF(Util.csv(server.getPlayers()));
        }
      }
    }
    if (subChannel.equals("GetServers")) {
      out.writeUTF("GetServers");
      out.writeUTF(Util.csv(bungee.getServers().keySet()));
    }
    if (subChannel.equals("Message")) {
      String target=in.readUTF();
      String message=in.readUTF();
      if (target.equals("ALL")) {
        for (        ProxiedPlayer player : bungee.getPlayers()) {
          player.sendMessage(message);
        }
      }
 else {
        ProxiedPlayer player=bungee.getPlayer(target);
        if (player != null) {
          player.sendMessage(message);
        }
      }
    }
    if (subChannel.equals("GetServer")) {
      out.writeUTF("GetServer");
      out.writeUTF(server.getInfo().getName());
    }
    if (subChannel.equals("UUID")) {
      out.writeUTF("UUID");
      out.writeUTF(con.getUUID());
    }
    if (subChannel.equals("UUIDOther")) {
      ProxiedPlayer player=bungee.getPlayer(in.readUTF());
      if (player != null) {
        out.writeUTF("UUIDOther");
        out.writeUTF(player.getName());
        out.writeUTF(player.getUUID());
      }
    }
    if (subChannel.equals("ServerIP")) {
      ServerInfo info=bungee.getServerInfo(in.readUTF());
      if (info != null && !info.getAddress().isUnresolved()) {
        out.writeUTF("ServerIP");
        out.writeUTF(info.getName());
        out.writeUTF(info.getAddress().getAddress().getHostAddress());
        out.writeShort(info.getAddress().getPort());
      }
    }
    if (subChannel.equals("KickPlayer")) {
      ProxiedPlayer player=bungee.getPlayer(in.readUTF());
      if (player != null) {
        String kickReason=in.readUTF();
        player.disconnect(new TextComponent(kickReason));
      }
    }
    if (out != null) {
      byte[] b=out.toByteArray();
      if (b.length != 0) {
        server.sendData("BungeeCord",b);
      }
    }
    throw CancelSendSignal.INSTANCE;
  }
}

@Override public void handle(Chat chat) throws Exception {
  int maxLength=(con.getPendingConnection().getVersion() >= ProtocolConstants.MINECRAFT_1_11) ? 256 : 100;
  Preconditions.checkArgument(chat.getMessage().length() <= maxLength,"Chat message too long");
  ChatEvent chatEvent=new ChatEvent(con,con.getServer(),chat.getMessage());
  if (!bungee.getPluginManager().callEvent(chatEvent).isCancelled()) {
    chat.setMessage(chatEvent.getMessage());
    if (!chatEvent.isCommand() || !bungee.getPluginManager().dispatchCommand(con,chat.getMessage().substring(1))) {
      con.getServer().unsafe().sendPacket(chat);
    }
  }
  throw CancelSendSignal.INSTANCE;
}

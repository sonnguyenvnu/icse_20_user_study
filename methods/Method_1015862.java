@Override public void broadcast(BaseComponent message){
  getConsole().sendMessage(message.toLegacyText());
  broadcast(new Chat(ComponentSerializer.toString(message)));
}

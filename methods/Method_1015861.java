@Override public void broadcast(BaseComponent... message){
  getConsole().sendMessage(BaseComponent.toLegacyText(message));
  broadcast(new Chat(ComponentSerializer.toString(message)));
}

public void highlight(IMChatConsole console){
  int i=tabbedChat.indexOfComponent(console);
  if (i >= 0 && tabbedChat instanceof ClosableTabHost) {
    ((ClosableTabHost)tabbedChat).bling(i,console.getName());
  }
}

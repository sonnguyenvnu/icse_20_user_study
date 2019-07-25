protected final void reply(WebSocketSession session,Event event,String text){
  reply(session,event,new Message(text));
}

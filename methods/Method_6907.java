public void sendBotStart(final TLRPC.User user,String botHash){
  if (user == null) {
    return;
  }
  TLRPC.TL_messages_startBot req=new TLRPC.TL_messages_startBot();
  req.bot=getInputUser(user);
  req.peer=getInputPeer(user.id);
  req.start_param=botHash;
  req.random_id=Utilities.random.nextLong();
  ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> {
    if (error != null) {
      return;
    }
    processUpdates((TLRPC.Updates)response,false);
  }
);
}

public TLRPC.Peer getPeer(int id){
  TLRPC.Peer inputPeer;
  if (id < 0) {
    TLRPC.Chat chat=getChat(-id);
    if (chat instanceof TLRPC.TL_channel || chat instanceof TLRPC.TL_channelForbidden) {
      inputPeer=new TLRPC.TL_peerChannel();
      inputPeer.channel_id=-id;
    }
 else {
      inputPeer=new TLRPC.TL_peerChat();
      inputPeer.chat_id=-id;
    }
  }
 else {
    TLRPC.User user=getUser(id);
    inputPeer=new TLRPC.TL_peerUser();
    inputPeer.user_id=id;
  }
  return inputPeer;
}

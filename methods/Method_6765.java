public boolean isSavedFromMegagroup(){
  if (messageOwner.fwd_from != null && messageOwner.fwd_from.saved_from_peer != null && messageOwner.fwd_from.saved_from_peer.channel_id != 0) {
    TLRPC.Chat chat=MessagesController.getInstance(currentAccount).getChat(messageOwner.fwd_from.saved_from_peer.channel_id);
    return ChatObject.isMegagroup(chat);
  }
  return false;
}

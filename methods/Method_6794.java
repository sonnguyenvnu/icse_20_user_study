public boolean needDrawForwarded(){
  return (messageOwner.flags & TLRPC.MESSAGE_FLAG_FWD) != 0 && messageOwner.fwd_from != null && (messageOwner.fwd_from.saved_from_peer == null || messageOwner.fwd_from.saved_from_peer.channel_id != messageOwner.fwd_from.channel_id) && UserConfig.getInstance(currentAccount).getClientUserId() != getDialogId();
}

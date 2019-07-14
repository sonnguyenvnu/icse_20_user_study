public boolean isOutOwner(){
  if (!messageOwner.out || messageOwner.from_id <= 0 || messageOwner.post) {
    return false;
  }
  if (messageOwner.fwd_from == null) {
    return true;
  }
  int selfUserId=UserConfig.getInstance(currentAccount).getClientUserId();
  if (getDialogId() == selfUserId) {
    return messageOwner.fwd_from.from_id == selfUserId && (messageOwner.fwd_from.saved_from_peer == null || messageOwner.fwd_from.saved_from_peer.user_id == selfUserId) || messageOwner.fwd_from.saved_from_peer != null && messageOwner.fwd_from.saved_from_peer.user_id == selfUserId;
  }
  return messageOwner.fwd_from.saved_from_peer == null || messageOwner.fwd_from.saved_from_peer.user_id == selfUserId;
}

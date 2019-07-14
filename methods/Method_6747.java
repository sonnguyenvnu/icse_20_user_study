public boolean needDrawAvatar(){
  return isFromUser() || eventId != 0 || messageOwner.fwd_from != null && messageOwner.fwd_from.saved_from_peer != null;
}

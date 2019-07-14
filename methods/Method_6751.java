public boolean isForwardedChannelPost(){
  return messageOwner.from_id <= 0 && messageOwner.fwd_from != null && messageOwner.fwd_from.channel_post != 0;
}

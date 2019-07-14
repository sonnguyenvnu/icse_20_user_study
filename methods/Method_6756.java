public int getChannelId(){
  if (messageOwner.to_id != null) {
    return messageOwner.to_id.channel_id;
  }
  return 0;
}

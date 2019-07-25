public void unsubscribe(String channelId){
  if (channelId == null) {
    return;
  }
  postUrlData(UNSUBSCRIBE_URL,mSubPostBody.replace("%CHANNEL_ID%",channelId));
}

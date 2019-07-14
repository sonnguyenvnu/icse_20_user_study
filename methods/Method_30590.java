public Broadcast toBroadcast(){
  if (content == null || content.type == null) {
    return null;
  }
  Broadcast contentBroadcast=contentToBroadcast();
  if (rebroadcaster == null) {
    return contentBroadcast;
  }
  Broadcast broadcast=new Broadcast();
  broadcast.action="??";
  broadcast.author=rebroadcaster;
  broadcast.createTime=createTime;
  broadcast.id=id;
  broadcast.rebroadcastedBroadcast=contentBroadcast;
  broadcast.shareUrl="https://www.douban.com/doubanapp/dispatch?uri=/status/" + id + "/";
  broadcast.uri=DoubanUtils.makeBroadcastUri(id);
  return broadcast;
}

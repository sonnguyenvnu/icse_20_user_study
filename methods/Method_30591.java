private Broadcast contentToBroadcast(){
  if (content.broadcast != null) {
    return content.broadcast;
  }
  Broadcast broadcast=new Broadcast();
  broadcast.action=action;
  if (content.author != null) {
    broadcast.author=content.author;
  }
 else   if (owner != null) {
    broadcast.author=owner.toSimpleUser();
  }
 else {
    broadcast.author=new SimpleUser();
    broadcast.author.avatar="";
    broadcast.author.id=0;
    broadcast.author.type="user";
    broadcast.author.name=ownerAlternaitveLabel != null ? ownerAlternaitveLabel.text : "";
    broadcast.author.uri="";
    broadcast.author.url="";
  }
  broadcast.attachment=content.toBroadcastAttachment();
  broadcast.commentCount=commentCount;
  broadcast.id=content.id;
  broadcast.likeCount=reactionCount;
  broadcast.isLiked=reactionType > 0;
  broadcast.rebroadcastCount=rebroadcastCount;
  broadcast.shareUrl=content.shareUrl;
  broadcast.uri=content.uri;
  return broadcast;
}

public PublicPostBean cover(PublicPostEntity posterMessageEntity){
  User user=UserManager.getInstance().cover(UserDBManager.getInstance().getUser(posterMessageEntity.getUid()));
  PublicPostBean posterMessage=new PublicPostBean();
  posterMessage.setAuthor(user);
  posterMessage.setCreateTime(TimeUtil.getTime(posterMessageEntity.getCreatedTime(),"yyyy-MM-dd HH:mm:ss"));
  posterMessage.setUpdatedAt(TimeUtil.getTime(posterMessageEntity.getUpdatedTime(),"yyyy-MM-dd HH:mm:ss"));
  posterMessage.setCommentCount(posterMessageEntity.getCommentCount());
  posterMessage.setLikeCount(posterMessageEntity.getLikeCount());
  posterMessage.setContent(posterMessageEntity.getContent());
  posterMessage.setLocation(posterMessageEntity.getLocation());
  posterMessage.setLikeList(posterMessageEntity.getLikeList());
  posterMessage.setMsgType(posterMessageEntity.getMsgType());
  posterMessage.setShareCount(posterMessageEntity.getShareCount());
  posterMessage.setObjectId(posterMessageEntity.getPid());
  return posterMessage;
}

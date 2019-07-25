public PublicPostEntity cover(PublicPostBean bean){
  PublicPostEntity entity=new PublicPostEntity();
  entity.setSendStatus(bean.getSendStatus());
  entity.setCommentCount(bean.getCommentCount());
  entity.setLikeCount(bean.getLikeCount());
  entity.setContent(bean.getContent());
  entity.setLocation(bean.getLocation());
  entity.setLikeList(bean.getLikeList());
  entity.setPid(bean.getObjectId());
  entity.setUid(bean.getAuthor().getObjectId());
  entity.setMsgType(bean.getMsgType());
  entity.setShareCount(bean.getShareCount());
  entity.setCreatedTime(TimeUtil.getTime(bean.getCreatedAt(),"yyyy-MM-dd HH:mm:ss"));
  if (bean.getUpdatedAt() != null) {
    entity.setUpdatedTime(TimeUtil.getTime(bean.getUpdatedAt(),"yyyy-MM-dd HH:mm:ss"));
  }
  return entity;
}

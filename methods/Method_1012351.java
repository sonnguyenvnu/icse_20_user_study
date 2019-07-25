public PostCommentEntity cover(PublicCommentBean item){
  PostCommentEntity entity=new PostCommentEntity();
  entity.setUid(item.getUser().getObjectId());
  entity.setPid(item.getPost().getObjectId());
  entity.setSendStatus(item.getSendStatus());
  entity.setContent(item.getContent());
  entity.setCreatedTime(TimeUtil.getTime(item.getCreatedAt(),"yyyy-MM-dd HH:mm:ss"));
  entity.setUpdatedTime(TimeUtil.getTime(item.getUpdatedAt(),"yyyy-MM-dd HH:mm:ss"));
  entity.setCid(item.getObjectId());
  return entity;
}

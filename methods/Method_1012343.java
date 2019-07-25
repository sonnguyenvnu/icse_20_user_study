public PublicCommentBean cover(PostCommentEntity entity){
  DaoSession daoSession=UserDBManager.getInstance().getDaoSession();
  PublicCommentBean posterComments=new PublicCommentBean();
  posterComments.setObjectId(entity.getCid());
  posterComments.setSendStatus(entity.getSendStatus());
  posterComments.setContent(entity.getContent());
  posterComments.setCreatedTime(TimeUtil.getTime(entity.getCreatedTime(),"yyyy-MM-dd HH:mm:ss"));
  posterComments.setUpdatedAt(TimeUtil.getTime(entity.getUpdatedTime(),"yyyy-MM-dd HH:mm:ss"));
  PublicPostEntity publicPostEntity=null;
  publicPostEntity=daoSession.getPublicPostEntityDao().queryBuilder().where(PublicPostEntityDao.Properties.Pid.eq(entity.getPid())).build().list().get(0);
  PublicPostBean publicPostBean=cover(publicPostEntity);
  posterComments.setPost(publicPostBean);
  User commentUser;
  if (!entity.getUid().equals(UserManager.getInstance().getCurrentUserObjectId())) {
    commentUser=UserManager.getInstance().cover(daoSession.getUserEntityDao().queryBuilder().where(UserEntityDao.Properties.Uid.eq(entity.getUid())).build().list().get(0));
  }
 else {
    commentUser=UserManager.getInstance().getCurrentUser();
  }
  posterComments.setUser(commentUser);
  return posterComments;
}

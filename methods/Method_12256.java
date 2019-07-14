@Transactional(rollbackFor=Exception.class) public void insertMs(MiaoShaMessageVo miaoShaMessageVo){
  MiaoShaMessageUser mu=new MiaoShaMessageUser();
  mu.setUserId(miaoShaMessageVo.getUserId());
  mu.setMessageId(miaoShaMessageVo.getMessageId());
  messageDao.insertMiaoShaMessageUser(mu);
  MiaoShaMessageInfo miaoshaMessage=new MiaoShaMessageInfo();
  miaoshaMessage.setContent(miaoShaMessageVo.getContent());
  miaoshaMessage.setStatus(miaoShaMessageVo.getStatus());
  miaoshaMessage.setMessageType(miaoShaMessageVo.getMessageType());
  miaoshaMessage.setSendType(miaoShaMessageVo.getSendType());
  miaoshaMessage.setMessageId(miaoShaMessageVo.getMessageId());
  miaoshaMessage.setCreateTime(new Date());
  miaoshaMessage.setMessageHead(miaoShaMessageVo.getMessageHead());
  messageDao.insertMiaoShaMessage(miaoshaMessage);
}

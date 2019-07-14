@Override public int changePayState(String id,Integer state){
  Pay pay=getPay(id);
  pay.setState(state);
  pay.setUpdateTime(new Date());
  payDao.saveAndFlush(pay);
  return 1;
}

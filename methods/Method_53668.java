@Override public int addPay(Pay pay){
  pay.setId(UUID.randomUUID().toString());
  pay.setCreateTime(new Date());
  pay.setState(0);
  payDao.save(pay);
  return 1;
}

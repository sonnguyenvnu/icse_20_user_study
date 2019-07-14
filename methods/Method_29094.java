@Override public List<ClientInstanceException> getInstanceExceptionStat(String ip,long collectTime){
  try {
    return appClientExceptionStatDao.getInstanceExceptionStat(ip,collectTime);
  }
 catch (  Exception e) {
    logger.error(e.getMessage(),e);
    return Collections.emptyList();
  }
}

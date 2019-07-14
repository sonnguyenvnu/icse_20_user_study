@Override public List<ServerStatus> queryServerNet(String ip,String date){
  try {
    return serverStatusDao.queryServerNet(ip,date);
  }
 catch (  Exception e) {
    logger.error("queryServerNet err ip=" + ip + " date=" + date,e);
  }
  return new ArrayList<ServerStatus>(0);
}

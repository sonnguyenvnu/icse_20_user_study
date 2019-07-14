@Override public List<ServerStatus> queryServerOverview(String ip,String date){
  try {
    return serverStatusDao.queryServerOverview(ip,date);
  }
 catch (  Exception e) {
    logger.error("queryServerOverview err ip=" + ip + " date=" + date,e);
  }
  return new ArrayList<ServerStatus>(0);
}

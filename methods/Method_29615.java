@Override public List<ServerStatus> queryServerStatus(String ip,String date){
  try {
    return serverStatusDao.queryServerStatus(ip,date);
  }
 catch (  Exception e) {
    logger.error("queryServerStatus err ip=" + ip + " date=" + date,e);
  }
  return new ArrayList<ServerStatus>(0);
}

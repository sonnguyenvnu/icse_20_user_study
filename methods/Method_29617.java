@Override public List<ServerStatus> queryServerCpu(String ip,String date){
  try {
    return serverStatusDao.queryServerCpu(ip,date);
  }
 catch (  Exception e) {
    logger.error("queryServerCpu err ip=" + ip + " date=" + date,e);
  }
  return new ArrayList<ServerStatus>(0);
}

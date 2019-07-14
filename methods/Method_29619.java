@Override public List<ServerStatus> queryServerDisk(String ip,String date){
  try {
    return serverStatusDao.queryServerDisk(ip,date);
  }
 catch (  Exception e) {
    logger.error("queryServerDisk err ip=" + ip + " date=" + date,e);
  }
  return new ArrayList<ServerStatus>(0);
}

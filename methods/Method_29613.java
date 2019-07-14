@Override public ServerInfo queryServerInfo(String ip){
  try {
    return serverStatusDao.queryServerInfo(ip);
  }
 catch (  Exception e) {
    logger.error("query err:" + ip,e);
  }
  return null;
}

public Integer saveAndUpdateServerInfo(Server server){
  if (server.getHost() == null || server.getNmon() == null || server.getCpus() == 0 || server.getCpuModel() == null || server.getKernel() == null || server.getUlimit() == null) {
    return null;
  }
  try {
    return serverStatusDao.saveAndUpdateServerInfo(server);
  }
 catch (  Exception e) {
    logger.error("saveAndUpdateServerInfo err server=" + server,e);
  }
  return null;
}

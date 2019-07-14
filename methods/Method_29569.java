private InstanceInfo getInstanceInfo(String hostPort){
  String[] hostPortArr=hostPort.split(":");
  String host=hostPortArr[0];
  int port=NumberUtils.toInt(hostPortArr[1]);
  return instanceDao.getAllInstByIpAndPort(host,port);
}

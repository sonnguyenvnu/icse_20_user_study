/** 
 * ???????????
 * @param ip
 * @param session
 */
private OSInfo initNmon(String ip,SSHSession session){
  String version=getNMONVersion(ip,session);
  OSInfo osInfo=getOSInfo(ip,session);
  OS os=null;
  if (null == version) {
    logger.warn("{} not exist {}",ip,getNmonFile());
    os=OSFactory.getOS(osInfo);
  }
 else {
    logger.warn("{} {} version err:" + version,ip,getNmonFile());
    os=OSFactory.getDefaultOS(osInfo);
  }
  if (os == null) {
    logger.error("unkonw os info={}",osInfo);
    return null;
  }
  File nmonFile=NMONFileFactory.getNMONFile(os);
  if (nmonFile == null) {
    logger.warn("{} no corresponding nmon file",os);
    nmonFile=NMONFileFactory.getNMONFile(OSFactory.getDefaultOS(osInfo));
  }
  sendNMONToServer(ip,session,nmonFile);
  return osInfo;
}

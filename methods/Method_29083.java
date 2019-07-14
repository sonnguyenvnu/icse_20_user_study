/** 
 * ????????
 * @param clientVersion
 * @return
 */
private boolean checkClientVersion(String clientVersion){
  if (StringUtils.isBlank(clientVersion)) {
    return false;
  }
  List<String> goodVersions=Lists.newArrayList(ConstUtils.GOOD_CLIENT_VERSIONS.split(ConstUtils.COMMA));
  List<String> warnVersions=Lists.newArrayList(ConstUtils.WARN_CLIENT_VERSIONS.split(ConstUtils.COMMA));
  if (goodVersions.contains(clientVersion) && warnVersions.contains(clientVersion)) {
    logger.error("status: {}, message: {}",ClientStatusEnum.ERROR.getStatus(),"ERROR: client is TOO old or NOT recognized, please update NOW!");
    return false;
  }
  return true;
}

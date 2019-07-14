private boolean checkClientVersion(long appId,String clientVersion,Model model){
  List<String> goodVersions=Lists.newArrayList(ConstUtils.GOOD_CLIENT_VERSIONS.split(ConstUtils.COMMA));
  List<String> warnVersions=Lists.newArrayList(ConstUtils.WARN_CLIENT_VERSIONS.split(ConstUtils.COMMA));
  boolean versionOk=true;
  if (goodVersions.contains(clientVersion)) {
    model.addAttribute("status",ClientStatusEnum.GOOD.getStatus());
    model.addAttribute("message","appId:" + appId + " client is up to date, Cheers!");
  }
 else   if (warnVersions.contains(clientVersion)) {
    model.addAttribute("status",ClientStatusEnum.WARN.getStatus());
    model.addAttribute("message","WARN: client is NOT the newest, please update!");
  }
 else {
    model.addAttribute("status",ClientStatusEnum.ERROR.getStatus());
    model.addAttribute("message","ERROR: client is TOO old or NOT recognized, please update NOW!");
    versionOk=false;
  }
  return versionOk;
}

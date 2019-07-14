/** 
 * ????UniqueName????????????????????interface:version[:uniqueId]
 * @param interfaceConfig ??????????????
 * @return ??????
 */
public static String getUniqueName(AbstractInterfaceConfig interfaceConfig){
  String version=interfaceConfig.getVersion();
  String uniqueId=interfaceConfig.getUniqueId();
  return interfaceConfig.getInterfaceId() + (StringUtils.isEmpty(version) ? ":1.0" : ":" + version) + (StringUtils.isEmpty(uniqueId) ? "" : ":" + uniqueId);
}

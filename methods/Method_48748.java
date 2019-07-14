private String computeUniqueInstanceId(Configuration config){
  final String suffix=getSuffix(config);
  final String uid=getUid(config);
  String instanceId=uid + suffix;
  for (  char c : ConfigElement.ILLEGAL_CHARS) {
    instanceId=StringUtils.replaceChars(instanceId,c,'-');
  }
  return instanceId;
}

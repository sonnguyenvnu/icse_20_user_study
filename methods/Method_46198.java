/** 
 * Convert child data to attribute list.
 * @param config       the interface config
 * @param overridePath the override path
 * @param currentData  the current data
 * @return the attribute list
 * @throws UnsupportedEncodingException decode exception
 */
static List<Map<String,String>> convertOverrideToAttributes(AbstractInterfaceConfig config,String overridePath,List<ChildData> currentData) throws UnsupportedEncodingException {
  List<Map<String,String>> attributes=new ArrayList<Map<String,String>>();
  if (CommonUtils.isEmpty(currentData)) {
    return attributes;
  }
  for (  ChildData childData : currentData) {
    String url=URLDecoder.decode(childData.getPath().substring(overridePath.length() + 1),"UTF-8");
    if (config instanceof ConsumerConfig) {
      if (StringUtils.isNotEmpty(url) && StringUtils.isNotEmpty(SystemInfo.getLocalHost()) && url.contains("://" + SystemInfo.getLocalHost() + "?")) {
        attributes.add(convertConfigToAttribute(overridePath,childData,false));
      }
    }
  }
  return attributes;
}

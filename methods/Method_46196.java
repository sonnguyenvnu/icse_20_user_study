/** 
 * Convert child data to attribute list.
 * @param configPath  the config path
 * @param currentData the current data
 * @return the attribute list
 */
static List<Map<String,String>> convertConfigToAttributes(String configPath,List<ChildData> currentData){
  List<Map<String,String>> attributes=new ArrayList<Map<String,String>>();
  if (CommonUtils.isEmpty(currentData)) {
    return attributes;
  }
  for (  ChildData childData : currentData) {
    attributes.add(convertConfigToAttribute(configPath,childData,false));
  }
  return attributes;
}

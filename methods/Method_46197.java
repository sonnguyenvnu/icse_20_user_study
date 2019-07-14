/** 
 * Convert child data to attribute.
 * @param configPath the config path
 * @param childData  the child data
 * @param removeType is remove type
 * @return the attribute
 */
static Map<String,String> convertConfigToAttribute(String configPath,ChildData childData,boolean removeType){
  String attribute=childData.getPath().substring(configPath.length() + 1);
  return Collections.singletonMap(attribute,removeType ? RpcConfigs.getStringValue(attribute) : StringSerializer.decode(childData.getData()));
}

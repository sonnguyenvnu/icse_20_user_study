/** 
 * ?????????keys
 * @param moduleName ????
 * @return remoteKeys
 */
public List<String> removeKeys(String moduleName){
  List<String> allKeys=new ArrayList<>();
  for (  Channel channel : channels) {
    if (moduleName.equals(getModuleName(channel))) {
      allKeys.add(channel.remoteAddress().toString());
    }
  }
  return allKeys;
}

/** 
 * ?????????????????
 * @param map  ??map
 * @param keys ??key
 * @return ???
 */
static String getValue(Map<String,String> map,String... keys){
  if (CommonUtils.isEmpty(map)) {
    return null;
  }
  for (  String key : keys) {
    String val=map.get(key);
    if (val != null) {
      return val;
    }
  }
  return null;
}

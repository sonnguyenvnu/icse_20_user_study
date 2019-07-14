/** 
 * ????key??????????
 * @param map  ??map
 * @param keys ??key
 */
static void removeOldKeys(Map<String,String> map,String... keys){
  if (CommonUtils.isEmpty(map)) {
    return;
  }
  for (  String key : keys) {
    map.remove(key);
  }
}

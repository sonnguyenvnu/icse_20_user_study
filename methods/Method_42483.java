/** 
 * ???????Map<String, Integer>.
 * @return
 */
@SuppressWarnings({"unchecked","rawtypes"}) public Map<Integer,Integer> getNotifyRuleMap(){
  return (Map)JSONObject.parseObject(getNotifyRule());
}

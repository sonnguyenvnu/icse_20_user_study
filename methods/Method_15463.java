/** 
 * set keys for group by
 * @param keys key0, key1, key2 ...
 * @return {@link #setGroup(String)}
 */
public JSONObject setGroup(String... keys){
  return setGroup(StringUtil.getString(keys,true));
}

/** 
 * set keys for order by
 * @param keys  key0, key1+, key2- ...
 * @return {@link #setOrder(String)}
 */
public JSONObject setOrder(String... keys){
  return setOrder(StringUtil.getString(keys,true));
}

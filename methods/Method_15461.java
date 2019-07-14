/** 
 * set keys need to be returned
 * @param keys  key0, key1, key2 ...
 * @return {@link #setColumn(String)}
 */
public JSONObject setColumn(String... keys){
  return setColumn(StringUtil.getString(keys,true));
}

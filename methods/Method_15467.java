/** 
 * @param key
 * @param keys  path = keys[0] + "/" + keys[1] + "/" + keys[2] + ...
 * @return {@link #puts(String,Object)}
 */
public JSONObject putsPath(String key,String... keys){
  return puts(key + "@",StringUtil.getString(keys,"/"));
}

/** 
 * @param key
 * @param isEmpty
 * @return {@link #puts(String,Object)}
 */
public JSONObject putsEmpty(String key,boolean isEmpty,boolean trim){
  return puts(key + "{}",SQL.isEmpty(key,isEmpty,trim));
}

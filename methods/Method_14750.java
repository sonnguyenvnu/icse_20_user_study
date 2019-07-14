/** 
 * puts key-value in object into this
 * @param map
 * @return this
 */
public JSONObject putsAll(Map<? extends String,? extends Object> map){
  putAll(map);
  return this;
}

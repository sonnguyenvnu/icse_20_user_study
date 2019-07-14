/** 
 * set keys for order by
 * @param keys  "key0,key1+,key2-..."
 * @return
 */
public JSONObject setOrder(String keys){
  return puts(KEY_ORDER,keys);
}

/** 
 * set keys for group by
 * @param keys  "key0,key1,key2..."
 * @return
 */
public JSONObject setGroup(String keys){
  return puts(KEY_GROUP,keys);
}

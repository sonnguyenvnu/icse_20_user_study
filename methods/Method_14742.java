/** 
 * set keys need to be returned
 * @param keys  "key0,key1,key2..."
 * @return
 */
public JSONObject setColumn(String keys){
  return puts(KEY_COLUMN,keys);
}

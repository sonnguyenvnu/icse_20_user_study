/** 
 * ????????
 * @param key ?
 * @return ?????
 */
public LinkedList<Map.Entry<String,V>> commonPrefixSearchWithValue(String key){
  char[] chars=key.toCharArray();
  return commonPrefixSearchWithValue(chars,0);
}

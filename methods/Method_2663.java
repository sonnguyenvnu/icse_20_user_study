/** 
 * ??????????trie?
 * @param keyValueMap ?????map
 * @return ????
 */
public int build(TreeMap<String,V> keyValueMap){
  assert keyValueMap != null;
  Set<Map.Entry<String,V>> entrySet=keyValueMap.entrySet();
  return build(entrySet);
}

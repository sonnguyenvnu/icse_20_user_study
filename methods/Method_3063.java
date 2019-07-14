/** 
 * ????
 * @param key
 * @return
 */
public static LinkedList<Map.Entry<String,CoreDictionary.Attribute>> commonPrefixSearch(String key){
  return trie.commonPrefixSearchWithValue(key);
}

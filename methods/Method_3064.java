/** 
 * ????
 * @param chars
 * @param begin
 * @return
 */
public static LinkedList<Map.Entry<String,CoreDictionary.Attribute>> commonPrefixSearch(char[] chars,int begin){
  return trie.commonPrefixSearchWithValue(chars,begin);
}

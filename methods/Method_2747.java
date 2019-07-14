/** 
 * ???????
 * @param word
 * @return
 */
public int getLongestSuffixLength(String word){
  word=reverse(word);
  LinkedList<Map.Entry<String,Integer>> suffixList=trie.commonPrefixSearchWithValue(word);
  if (suffixList.size() == 0)   return 0;
  return suffixList.getLast().getValue();
}

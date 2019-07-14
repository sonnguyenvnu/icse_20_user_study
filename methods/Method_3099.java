/** 
 * ???????????
 * @param charArray
 * @param trie
 * @return
 */
protected static List<Pinyin> segLongest(char[] charArray,AhoCorasickDoubleArrayTrie<Pinyin[]> trie){
  return segLongest(charArray,trie,true);
}

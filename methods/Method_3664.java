/** 
 * ?????????
 * @param word
 * @param attribute
 * @return
 */
public static boolean setAttribute(String word,CoreDictionary.Attribute attribute){
  if (attribute == null)   return false;
  if (CoreDictionary.trie.set(word,attribute))   return true;
  if (CustomDictionary.dat.set(word,attribute))   return true;
  if (CustomDictionary.trie == null) {
    CustomDictionary.add(word);
  }
  CustomDictionary.trie.put(word,attribute);
  return true;
}

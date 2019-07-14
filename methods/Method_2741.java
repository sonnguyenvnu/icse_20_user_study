/** 
 * ?????????
 * @return
 */
public StringDictionary reverse(){
  StringDictionary dictionary=new StringDictionary(separator);
  for (  Map.Entry<String,String> entry : entrySet()) {
    dictionary.trie.put(entry.getValue(),entry.getKey());
  }
  return dictionary;
}

/** 
 * ????
 * @param key
 * @param begin
 * @return
 */
public LinkedList<Entry<String,V>> commonPrefixSearchWithValue(char[] key,int begin){
  LinkedList<Entry<String,Integer>> valueIndex=mdag.commonPrefixSearchWithValueIndex(key,begin);
  LinkedList<Entry<String,V>> entryList=new LinkedList<Entry<String,V>>();
  for (  Entry<String,Integer> entry : valueIndex) {
    entryList.add(new SimpleEntry<String,V>(entry.getKey(),valueList.get(entry.getValue())));
  }
  return entryList;
}

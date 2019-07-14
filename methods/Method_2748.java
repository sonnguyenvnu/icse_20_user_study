/** 
 * ???
 * @return
 */
public Set<Map.Entry<String,Integer>> entrySet(){
  Set<Map.Entry<String,Integer>> treeSet=new LinkedHashSet<Map.Entry<String,Integer>>();
  for (  Map.Entry<String,Integer> entry : trie.entrySet()) {
    treeSet.add(new AbstractMap.SimpleEntry<String,Integer>(reverse(entry.getKey()),entry.getValue()));
  }
  return treeSet;
}

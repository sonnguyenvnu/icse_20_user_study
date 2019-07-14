/** 
 * ??DAT
 * @param entrySet ???entrySet??????????????
 * @return
 */
public int build(Set<Map.Entry<String,V>> entrySet){
  List<String> keyList=new ArrayList<String>(entrySet.size());
  List<V> valueList=new ArrayList<V>(entrySet.size());
  for (  Map.Entry<String,V> entry : entrySet) {
    keyList.add(entry.getKey());
    valueList.add(entry.getValue());
  }
  return build(keyList,valueList);
}

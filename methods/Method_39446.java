/** 
 * Adds child map to the props on given prefix.
 */
public void addInnerMap(String prefix,final Map<?,?> map,final String profile){
  if (!StringUtil.endsWithChar(prefix,'.')) {
    prefix+=StringPool.DOT;
  }
  for (  Map.Entry<?,?> entry : map.entrySet()) {
    String key=entry.getKey().toString();
    key=prefix + key;
    setValue(key,entry.getValue().toString(),profile);
  }
}

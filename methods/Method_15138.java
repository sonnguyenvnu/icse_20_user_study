/** 
 * ROOT ????
 * @return
 */
public Set<String> getKeySet(){
  Map<String,String> map=getMap();
  return map == null ? null : map.keySet();
}

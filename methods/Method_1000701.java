/** 
 * ??????????????? Map ?????????? <p> ?????????????? <ul> <li>"^xxxxx" : ????? <li>"" : ??? Blank </ul>
 * @param map ??? Map
 * @return ????
 */
@Override public boolean match(Map<String,Object> map){
  if (null == map)   return false;
  if (this.size() == 0)   return true;
  for (  Map.Entry<String,Object> en : this.entrySet()) {
    String key=en.getKey();
    Object mtc=en.getValue();
    if (null == mtc) {
      if (map.containsKey(key))       return false;
    }
 else {
      Object val=map.get(key);
      if (!__match_val(mtc,val)) {
        return false;
      }
    }
  }
  return true;
}

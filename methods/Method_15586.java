/** 
 * @param pair
 * @param valueMap
 * @return
 */
public static Entry<Class<?>,Object> parseVariableEntry(String pair,Map<String,Object> valueMap){
  pair=StringUtil.getString(pair);
  Entry<Class<?>,Object> entry=new Entry<Class<?>,Object>();
  if (pair.isEmpty() == false) {
    int index=pair.contains(":") ? pair.indexOf(":") : -1;
    entry.setKey(CLASS_MAP.get(index < 0 ? Object.class.getSimpleName() : pair.substring(0,index)));
    entry.setValue(valueMap == null ? null : valueMap.get(pair.substring(index + 1,pair.length())));
  }
  return entry;
}

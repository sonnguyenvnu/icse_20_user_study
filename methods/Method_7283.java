private static Map<String,Integer> getDisplayNames(int field,Locale locale){
  Map<String,Integer> result=new HashMap<String,Integer>();
  insertValuesInMap(result,getDisplayNameArray(field,false,locale));
  insertValuesInMap(result,getDisplayNameArray(field,true,locale));
  return result.isEmpty() ? null : result;
}

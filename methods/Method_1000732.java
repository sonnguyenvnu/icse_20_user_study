/** 
 * ????
 * @param obj
 */
protected void each(Object obj){
  if (obj instanceof Map) {
    convertMap((Map<?,?>)obj);
  }
 else   if (obj instanceof List) {
    convertList((List<?>)obj);
  }
}

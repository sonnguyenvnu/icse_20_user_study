/** 
 * Adds profile property.
 */
public void putProfileProperty(final String key,final String value,final String profile,final boolean append){
  Map<String,PropsEntry> map=profileProperties.computeIfAbsent(profile,k -> new HashMap<>());
  put(profile,map,key,value,append);
}

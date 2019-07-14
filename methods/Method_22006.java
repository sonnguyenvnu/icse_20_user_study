/** 
 * ??????.
 * @param key ???
 * @param value ???
 */
public void put(final String key,final String value){
  JobPropertiesEnum jobPropertiesEnum=JobPropertiesEnum.from(key);
  if (null == jobPropertiesEnum || null == value) {
    return;
  }
  map.put(jobPropertiesEnum,value);
}

/** 
 * Returns inner map from the props with given prefix. Keys in returned map will not have the prefix.
 */
@SuppressWarnings("unchecked") public Map<String,Object> innerMap(final String prefix){
  initialize();
  return data.extract(null,activeProfiles,null,prefix);
}

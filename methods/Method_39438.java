/** 
 * Returns value of property, using active profiles, or  {@code null} if property not found.
 */
public String getValue(final String key){
  initialize();
  return data.lookupValue(key,activeProfiles);
}

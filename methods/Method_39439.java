/** 
 * Returns value of property, using active profiles or default value if not found.
 */
public String getValueOrDefault(final String key,final String defaultValue){
  initialize();
  final String value=data.lookupValue(key,activeProfiles);
  if (value == null) {
    return defaultValue;
  }
  return value;
}

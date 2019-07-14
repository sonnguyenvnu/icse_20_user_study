/** 
 * Resolves real name from JSON name.
 */
public String resolveRealName(final String jsonName){
  if (jsonNames == null) {
    return jsonName;
  }
  int jsonIndex=ArraysUtil.indexOf(jsonNames,jsonName);
  if (jsonIndex == -1) {
    return jsonName;
  }
  return realNames[jsonIndex];
}

/** 
 * search key match RegExp values
 * @param key
 * @param value
 * @param ignoreCase 
 * @return {@link #getRegExpString(String,Object[],int,boolean)}
 * @throws IllegalArgumentException 
 */
@JSONField(serialize=false) public String getRegExpString(String key,Object value,boolean ignoreCase) throws IllegalArgumentException {
  if (value == null) {
    return "";
  }
  Logic logic=new Logic(key);
  key=logic.getKey();
  Log.i(TAG,"getRegExpString key = " + key);
  JSONArray arr=newJSONArray(value);
  if (arr.isEmpty()) {
    return "";
  }
  return getRegExpString(key,arr.toArray(),logic.getType(),ignoreCase);
}

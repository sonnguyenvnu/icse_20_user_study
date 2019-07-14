/** 
 * search key match value
 * @param in
 * @return {@link #getSearchString(String,Object[],int)}
 * @throws IllegalArgumentException 
 */
@JSONField(serialize=false) public String getSearchString(String key,Object value) throws IllegalArgumentException {
  if (value == null) {
    return "";
  }
  Logic logic=new Logic(key);
  key=logic.getKey();
  Log.i(TAG,"getSearchString key = " + key);
  JSONArray arr=newJSONArray(value);
  if (arr.isEmpty()) {
    return "";
  }
  return getSearchString(key,arr.toArray(),logic.getType());
}

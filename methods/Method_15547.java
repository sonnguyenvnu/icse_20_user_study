/** 
 * WHERE key BETWEEN 'start' AND 'end'
 * @param key
 * @param value 'start,end'
 * @return LOGIC [ key BETWEEN 'start' AND 'end' ]
 * @throws IllegalArgumentException 
 */
@JSONField(serialize=false) public String getBetweenString(String key,Object value) throws IllegalArgumentException {
  if (value == null) {
    return "";
  }
  Logic logic=new Logic(key);
  key=logic.getKey();
  Log.i(TAG,"getBetweenString key = " + key);
  JSONArray arr=newJSONArray(value);
  if (arr.isEmpty()) {
    return "";
  }
  return getBetweenString(key,arr.toArray(),logic.getType());
}

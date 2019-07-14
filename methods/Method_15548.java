/** 
 * WHERE key contains value
 * @param key
 * @param value
 * @return 	{@link #getContainString(String,Object[],int)}
 * @throws NotExistException
 */
@JSONField(serialize=false) public String getContainString(String key,Object value) throws IllegalArgumentException {
  if (value == null) {
    return "";
  }
  Logic logic=new Logic(key);
  key=logic.getKey();
  Log.i(TAG,"getContainString key = " + key);
  return getContainString(key,newJSONArray(value).toArray(),logic.getType());
}

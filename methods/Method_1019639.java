/** 
 * Put a value in the JSONArray, where the value will be a JSONArray which is produced from a Collection.
 * @param value A Collection value.
 * @return this.
 */
public JSONArray put(Collection value){
  this.put(new JSONArray(value,this.isUpperCaseFirstChar()));
  return this;
}

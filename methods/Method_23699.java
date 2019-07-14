/** 
 * Return this dictionary as a String in JSON format.
 */
public String toJSON(){
  StringList items=new StringList();
  for (int i=0; i < count; i++) {
    items.append(JSONObject.quote(keys[i]) + ": " + values[i]);
  }
  return "{ " + items.join(", ") + " }";
}

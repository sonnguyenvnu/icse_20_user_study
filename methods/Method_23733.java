/** 
 * Return this dictionary as a String in JSON format.
 */
public String toJSON(){
  StringList temp=new StringList();
  for (  String item : this) {
    temp.append(JSONObject.quote(item));
  }
  return "[ " + temp.join(", ") + " ]";
}

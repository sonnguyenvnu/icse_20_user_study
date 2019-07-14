/** 
 * @webref input:files
 * @param input String to parse as a JSONObject
 * @see PApplet#loadJSONObject(String)
 * @see PApplet#saveJSONObject(JSONObject,String)
 */
public JSONObject parseJSONObject(String input){
  return new JSONObject(new StringReader(input));
}

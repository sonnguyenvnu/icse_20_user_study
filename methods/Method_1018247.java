/** 
 * Encode a JSONObject.
 * @param jsonobject The JSONObject.
 * @throws JSONException
 */
public void encode(JSONObject jsonobject) throws JSONException {
  generate();
  writeJSON(jsonobject);
}

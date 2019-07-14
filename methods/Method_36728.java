public JSONObject optJsonObjectParam(String key){
  if (extras.has(key))   return extras.optJSONObject(key);
  if (style != null && style.extras != null)   return style.extras.optJSONObject(key);
  return null;
}

public JSONArray optJsonArrayParam(String key){
  if (extras.has(key)) {
    return extras.optJSONArray(key);
  }
  if (style != null && style.extras != null) {
    return style.extras.optJSONArray(key);
  }
  return null;
}

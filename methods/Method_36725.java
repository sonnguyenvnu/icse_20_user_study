public String optStringParam(String key){
  if (extras.has(key))   return extras.optString(key);
  if (style != null && style.extras != null)   return style.extras.optString(key);
  return "";
}

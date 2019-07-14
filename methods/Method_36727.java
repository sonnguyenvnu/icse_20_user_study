public boolean optBoolParam(String key){
  if (extras.has(key))   return extras.optBoolean(key);
  return style != null && style.extras != null && style.extras.optBoolean(key);
}

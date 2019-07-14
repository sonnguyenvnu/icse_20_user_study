public int optIntParam(String key){
  if (extras.has(key))   return extras.optInt(key);
  if (style != null && style.extras != null)   return style.extras.optInt(key);
  return 0;
}

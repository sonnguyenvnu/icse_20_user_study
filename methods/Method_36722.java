public Object optParam(String key){
  if (extras.has(key))   return extras.opt(key);
  if (style != null && style.extras != null)   return style.extras.opt(key);
  return null;
}

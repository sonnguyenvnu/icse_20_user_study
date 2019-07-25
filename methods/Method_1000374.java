public boolean has(String key){
  if (ME.equals(key))   return true;
  if (en.getField(key) != null)   return true;
  return ext.containsKey(key);
}

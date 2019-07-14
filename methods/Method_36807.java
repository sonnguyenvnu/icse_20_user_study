public boolean hasParam(String key){
  return extras.has(key) || style != null && style.extras != null && style.extras.has(key);
}

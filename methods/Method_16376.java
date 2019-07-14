@Override default Object put(String key,Object value){
  Object old=get(key);
  setProperty(key,value);
  return old;
}

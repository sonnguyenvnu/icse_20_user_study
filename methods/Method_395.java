public Integer getInteger(String key){
  Object value=get(key);
  return castToInt(value);
}

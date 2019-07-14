public Integer getInt(String key,Integer defaultValue){
  return returnIfValidOrDefaultIfNot(key,Integer.class,defaultValue);
}

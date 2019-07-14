public Boolean getBoolean(String key,Boolean defaultValue){
  return returnIfValidOrDefaultIfNot(key,Boolean.class,defaultValue);
}

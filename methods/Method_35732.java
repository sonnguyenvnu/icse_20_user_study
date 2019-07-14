public String getString(String key,String defaultValue){
  return returnIfValidOrDefaultIfNot(key,String.class,defaultValue);
}

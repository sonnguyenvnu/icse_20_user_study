static void checkKeyValidity(String key){
  Preconditions.checkArgument(!StringUtils.containsAny(key,new char[]{IndexProvider.REPLACEMENT_CHAR}),"Invalid key name containing reserved character %c provided: %s",IndexProvider.REPLACEMENT_CHAR,key);
}

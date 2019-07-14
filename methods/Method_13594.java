static Method lookupBlockHandler(Class<?> clazz,String name){
  return BLOCK_HANDLER_MAP.get(getKey(clazz,name));
}

static Method lookupFallback(Class<?> clazz,String name){
  return FALLBACK_MAP.get(getKey(clazz,name));
}

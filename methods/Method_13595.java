static void updateFallbackFor(Class<?> clazz,String name,Method method){
  if (clazz == null || StringUtil.isBlank(name)) {
    throw new IllegalArgumentException("Bad argument");
  }
  FALLBACK_MAP.put(getKey(clazz,name),method);
}

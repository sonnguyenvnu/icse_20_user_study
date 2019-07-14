public <T>T toJavaObject(Class<T> clazz,ParserConfig config,int features){
  if (clazz == Map.class) {
    return (T)this;
  }
  if (clazz == Object.class && !containsKey(JSON.DEFAULT_TYPE_KEY)) {
    return (T)this;
  }
  return TypeUtils.castToJavaBean(this,clazz,config);
}

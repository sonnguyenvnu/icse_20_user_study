public ObjectProxy fetch(String key){
  for (  IocContext c : contexts) {
    ObjectProxy re=c.fetch(key);
    if (null != re)     return re;
  }
  return null;
}

public <T>T attr(String key,Class<T> clzz){
  return (T)attributes.get((String)staticMethod(this.getClass(),"translateFromAlias",key));
}

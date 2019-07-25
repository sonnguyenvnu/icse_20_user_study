public Document attr(String key,Object obj){
  attributes.put((String)staticMethod(this.getClass(),"translateFromAlias",key),obj);
  return this;
}

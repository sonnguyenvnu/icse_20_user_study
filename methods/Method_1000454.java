public SqlContext attr(String name,Object value){
  if (null == attrs) {
    attrs=new HashMap<String,Object>();
  }
  attrs.put(name,value);
  return this;
}

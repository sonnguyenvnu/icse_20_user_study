public HtmlToken attr(String name,String value){
  Pair<String> attr=getAttr(name);
  if (null == attr) {
    attr=new Pair<String>(name,value);
    attributes.add(attr);
  }
 else {
    attr.setValue(value);
  }
  return this;
}

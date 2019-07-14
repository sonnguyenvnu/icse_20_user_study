public static Scope evalProperties(Scope unevaled,Scope s){
  Scope evaled=new Scope();
  for (  String field : unevaled.keySet()) {
    Map<String,Object> props=unevaled.lookupAllProps(field);
    for (    Map.Entry<String,Object> e : props.entrySet()) {
      Object v=e.getValue();
      if (v instanceof Node) {
        Value vValue=((Node)v).interp(s);
        evaled.put(field,e.getKey(),vValue);
      }
 else {
        _.abort("property is not a node, parser bug: " + v);
      }
    }
  }
  return evaled;
}

public Scope copy(){
  Scope ret=new Scope();
  for (  String name : table.keySet()) {
    Map<String,Object> props=new LinkedHashMap<>();
    props.putAll(table.get(name));
    ret.table.put(name,props);
  }
  return ret;
}

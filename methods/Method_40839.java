public void putProperties(String name,Map<String,Object> props){
  Map<String,Object> item=table.get(name);
  if (item == null) {
    item=new LinkedHashMap<>();
  }
  item.putAll(props);
  table.put(name,item);
}

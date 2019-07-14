public void put(String name,String key,Object value){
  Map<String,Object> item=table.get(name);
  if (item == null) {
    item=new LinkedHashMap<>();
  }
  item.put(key,value);
  table.put(name,item);
}

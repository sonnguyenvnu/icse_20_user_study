public void register(String typeName,Class type){
  typeMapping.putIfAbsent(typeName,type);
}

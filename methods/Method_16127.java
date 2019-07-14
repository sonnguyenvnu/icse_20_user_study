@Override public String getTable(String name){
  return getMapping().getOrDefault(name,staticMapping.getOrDefault(name,name));
}

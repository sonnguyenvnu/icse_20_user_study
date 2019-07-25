public Set<String> keys(){
  Set<String> names=new HashSet<String>(en.getMappingFields().size());
  names.add(ME);
  for (  MappingField mf : en.getMappingFields())   names.add(mf.getName());
  names.addAll(ext.keySet());
  return names;
}

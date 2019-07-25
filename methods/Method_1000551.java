public Set<String> names(){
  Set<String> list=new HashSet<String>();
  for (  IocContext c : contexts)   list.addAll(c.names());
  return list;
}

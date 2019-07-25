public Set<String> keys(){
  Set<String> ss=new HashSet<String>();
  ss.addAll(map.keySet());
  if (req != null)   ss.addAll((Collection<? extends String>)Lang.enum2collection(req.getParameterNames(),new HashSet<String>()));
  return ss;
}

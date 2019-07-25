public Set<String> keys(){
  if (req == null)   return new HashSet<String>();
  return (Set<String>)Lang.enum2collection(req.getParameterNames(),new HashSet<String>());
}

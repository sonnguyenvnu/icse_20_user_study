@Override public Collection<ClassNode> values(){
  return raw.keySet().stream().map(name -> get(name)).collect(Collectors.toSet());
}

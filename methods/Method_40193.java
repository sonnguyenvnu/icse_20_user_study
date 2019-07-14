@NotNull public Collection<Binding> values(){
  Set<Binding> ret=new HashSet<>();
  for (  Set<Binding> bs : table.values()) {
    ret.addAll(bs);
  }
  return ret;
}

@NotNull public Collection<Binding> values(){
  List<Binding> ret=new ArrayList<>();
  for (  List<Binding> bs : table.values()) {
    ret.addAll(bs);
  }
  return ret;
}

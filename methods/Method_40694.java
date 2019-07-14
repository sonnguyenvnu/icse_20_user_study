@NotNull public List<Binding> updateTagged(String id,String tag,@NotNull List<Binding> bs){
  return update(makeTagId(id,tag),bs);
}

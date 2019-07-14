@NotNull public List<Binding> updateTagged(String id,String tag,@NotNull Binding b){
  return update(makeTagId(id,tag),b);
}

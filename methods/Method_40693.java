public void insertTagged(String id,String tag,Node node,Type type,Binding.Kind kind){
  insert(makeTagId(id,tag),node,type,kind);
}

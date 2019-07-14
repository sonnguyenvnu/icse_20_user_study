/** 
 * If no bindings are found, or it is rebinding in the same thread of control to a new type, then create a new binding and rewrite/shadow the old one. Otherwise, use the exisitng binding and update the type.
 */
private Binding insertOrUpdate(Binding b,String id,Node loc,Type t,Binding.Kind k,int tag){
  if (b == null) {
    b=insertBinding(id,new Binding(id,loc,t,k,tag));
  }
 else   if (tag == b.tag && !b.getType().equals(t)) {
    b=insertBinding(id,new Binding(id,loc,t,k,tag));
  }
 else {
    b.addDef(loc);
    b.setType(UnionType.union(t,b.getType()));
  }
  return b;
}

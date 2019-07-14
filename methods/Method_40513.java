/** 
 * Adds a new binding for  {@code id}.  If a binding already existed, replaces its previous definitions, if any, with  {@code loc}.  Sets the binding's type to  {@code type} (not a union with the previous type).
 */
public Binding update(String id,Def loc,Type type,Binding.Kind kind){
  if (type == null) {
    throw new IllegalArgumentException("Null type: id=" + id + ", loc=" + loc);
  }
  Binding b=lookupScope(id);
  if (b == null) {
    return insertBinding(id,new Binding(id,loc,type,kind));
  }
  b.getDefs().clear();
  b.addDef(loc);
  b.setType(type);
  if (b.getType().isUnknownType()) {
    b.setKind(kind);
  }
  return b;
}

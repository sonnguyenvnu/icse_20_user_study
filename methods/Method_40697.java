/** 
 * Look up a name (String) in the current symbol table.  If not found, recurse on the parent table.
 */
@Nullable public List<Binding> lookup(@NotNull String name){
  List<Binding> b=getModuleBindingIfGlobal(name);
  if (b != null) {
    return b;
  }
 else {
    List<Binding> ent=lookupLocal(name);
    if (ent != null) {
      return ent;
    }
 else {
      if (parent != null) {
        return parent.lookup(name);
      }
 else {
        return null;
      }
    }
  }
}

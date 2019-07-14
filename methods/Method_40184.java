/** 
 * Look up a name in the module if it is declared as global, otherwise look it up locally.
 */
@Nullable public Set<Binding> lookupScope(String name){
  Set<Binding> b=getModuleBindingIfGlobal(name);
  if (b != null) {
    return b;
  }
 else {
    return lookupLocal(name);
  }
}

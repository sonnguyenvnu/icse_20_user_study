/** 
 * Look up a name in the module if it is declared as global, otherwise look it up locally.
 */
public Binding lookupScope(String name){
  Binding b=getModuleBindingIfGlobal(name);
  if (b != null) {
    return b;
  }
 else {
    return lookupLocal(name);
  }
}

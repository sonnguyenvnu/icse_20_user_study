/** 
 * If  {@code name} is declared as a global, return the module binding.
 */
private Binding getModuleBindingIfGlobal(String name){
  if (isGlobalName(name)) {
    Scope module=getGlobalTable();
    if (module != null && module != this) {
      return module.lookupLocal(name);
    }
  }
  return null;
}

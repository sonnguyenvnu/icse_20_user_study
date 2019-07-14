/** 
 * If  {@code name} is declared as a global, return the module binding.
 */
@Nullable private Set<Binding> getModuleBindingIfGlobal(@NotNull String name){
  if (isGlobalName(name)) {
    State module=getGlobalTable();
    if (module != this) {
      return module.lookupLocal(name);
    }
  }
  return null;
}

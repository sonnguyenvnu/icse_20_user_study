/** 
 * Loads (if necessary) and returns the specified built-in module.
 */
@Nullable public ModuleType get(@NotNull String name){
  if (!name.contains(".")) {
    return getModule(name);
  }
  String[] mods=name.split("\\.");
  Type type=getModule(mods[0]);
  if (type == null) {
    return null;
  }
  for (int i=1; i < mods.length; i++) {
    type=type.table.lookupType(mods[i]);
    if (!(type instanceof ModuleType)) {
      return null;
    }
  }
  return (ModuleType)type;
}

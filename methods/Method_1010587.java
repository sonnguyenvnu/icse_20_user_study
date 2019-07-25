@Override public SModule resolve(@NotNull SModuleReference reference){
  for (  SModule module : getModules()) {
    if (module.getModuleReference().equals(reference)) {
      return module;
    }
  }
  return null;
}

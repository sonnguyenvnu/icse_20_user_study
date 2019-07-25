@Override public SModule resolve(@NotNull SRepository repo){
  return repo.getModule(getModuleId());
}

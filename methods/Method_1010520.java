@Override public SModule resolve(@NotNull SModuleReference reference){
  return myRepository.getModule(reference.getModuleId());
}

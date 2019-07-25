private SModuleReference update(SModuleReference reference){
  SModule module=reference.resolve(myRepository);
  return module == null ? reference : module.getModuleReference();
}

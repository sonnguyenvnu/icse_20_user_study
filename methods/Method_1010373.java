@Override public SModel resolve(SRepository repo){
  if (myModuleReference != null) {
    final SRepository repository;
    if (repo == null) {
      repository=MPSModuleRepository.getInstance();
    }
 else {
      repository=repo;
    }
    Computable<SModel> c=() -> {
      SModule module=repository.getModule(myModuleReference.getModuleId());
      if (module == null) {
        return null;
      }
      return module.getModel(myModelId);
    }
;
    if (!repository.getModelAccess().canRead()) {
      LOG.warn("Attempt to resolve a model not from read action. What are you going to do with return value? Hint: at least, read. Please ensure proper model access then.",new Throwable());
      return new ModelAccessHelper(repository).runReadAction(c);
    }
 else {
      return c.compute();
    }
  }
  if (SModelRepository.getInstance() != null) {
    return SModelRepository.getInstance().getModelDescriptor(this);
  }
  return null;
}

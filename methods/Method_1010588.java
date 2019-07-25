@Override public SModel resolve(@NotNull org.jetbrains.mps.openapi.model.SModelReference reference){
  for (  SModel model : getModels()) {
    if (model.getReference().equals(reference)) {
      return model;
    }
  }
  return null;
}

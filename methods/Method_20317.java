@Override protected final void buildModels(){
  if (!isBuildingModels()) {
    throw new IllegalStateException("You cannot call `buildModels` directly. Call `setData` instead to trigger a model " + "refresh with new data.");
  }
  buildModels(currentData);
}

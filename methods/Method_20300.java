@Override protected final void buildModels(){
  if (!isBuildingModels()) {
    throw new IllegalEpoxyUsage("You cannot call `buildModels` directly. Call `setModels` instead.");
  }
  add(currentModels);
}

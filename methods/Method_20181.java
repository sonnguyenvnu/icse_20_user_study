private void assertIsBuildingModels(){
  if (!isBuildingModels()) {
    throw new IllegalEpoxyUsage("Can only call this when inside the `buildModels` method");
  }
}

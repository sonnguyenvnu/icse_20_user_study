private void assertNotBuildingModels(){
  if (isBuildingModels()) {
    throw new IllegalEpoxyUsage("Cannot call this from inside `buildModels`");
  }
}

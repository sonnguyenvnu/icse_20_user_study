boolean isModelAddedMultipleTimes(EpoxyModel<?> model){
  assertIsBuildingModels();
  int modelCount=0;
  int size=modelsBeingBuilt.size();
  for (int i=0; i < size; i++) {
    if (modelsBeingBuilt.get(i) == model) {
      modelCount++;
    }
  }
  return modelCount > 1;
}

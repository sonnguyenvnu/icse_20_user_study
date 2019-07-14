int getFirstIndexOfModelInBuildingList(EpoxyModel<?> model){
  assertIsBuildingModels();
  int size=modelsBeingBuilt.size();
  for (int i=0; i < size; i++) {
    if (modelsBeingBuilt.get(i) == model) {
      return i;
    }
  }
  return -1;
}

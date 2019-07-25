@NotNull public ModelCreationOptions calculate(@NotNull SModelName modelName){
  return ModelCreationOptions.startBuilding().setModelName(modelName.getValue()).finishBuilding();
}

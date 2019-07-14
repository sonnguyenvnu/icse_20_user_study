protected void validateModelHashCodesHaveNotChanged(T controller){
  List<EpoxyModel<?>> currentModels=controller.getAdapter().getCopyOfModels();
  for (int i=0; i < currentModels.size(); i++) {
    EpoxyModel model=currentModels.get(i);
    model.validateStateHasNotChangedSinceAdded("Model has changed since it was added to the controller.",i);
  }
}

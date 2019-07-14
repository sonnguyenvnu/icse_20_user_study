void clearModelFromStaging(EpoxyModel<?> model){
  if (stagedModel != model) {
    addCurrentlyStagedModelIfExists();
  }
  stagedModel=null;
}

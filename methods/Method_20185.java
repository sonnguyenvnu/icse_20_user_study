void addCurrentlyStagedModelIfExists(){
  if (stagedModel != null) {
    stagedModel.addTo(this);
  }
  stagedModel=null;
}

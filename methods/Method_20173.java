/** 
 * Whether an update to models is currently pending. This can either be because {@link #requestModelBuild()} was called, or because models are currently being built or diffon a background thread.
 */
public boolean hasPendingModelBuild(){
  return requestedModelBuildType != RequestedModelBuildType.NONE || threadBuildingModels != null || adapter.isDiffInProgress();
}

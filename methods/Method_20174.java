/** 
 * Cancels a pending call to  {@link #buildModels()} if one has been queued by {@link #requestModelBuild()}.
 */
public synchronized void cancelPendingModelBuild(){
  if (requestedModelBuildType != RequestedModelBuildType.NONE) {
    requestedModelBuildType=RequestedModelBuildType.NONE;
    modelBuildHandler.removeCallbacks(buildModelsRunnable);
  }
}

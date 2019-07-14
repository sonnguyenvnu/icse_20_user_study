/** 
 * Get the number of models added so far during the  {@link #buildModels()} phase. It is only validto call this from within that method. <p> This is different from the number of models currently on the adapter, since models on the adapter are not updated until after models are finished being built. To access current adapter count call  {@link #getAdapter()} and {@link EpoxyControllerAdapter#getItemCount()}
 */
protected int getModelCountBuiltSoFar(){
  assertIsBuildingModels();
  return modelsBeingBuilt.size();
}

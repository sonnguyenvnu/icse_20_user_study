/** 
 * Handy utility when the new model data is known/obtained beforehand, not through #createModel() factory
 */
protected void replace(@NotNull ModelLoadResult<jetbrains.mps.smodel.SModel> newModel){
  final ModelLoadingState oldState;
synchronized (myLoadLock) {
    oldState=getLoadingState();
    if (mySModel != null) {
      mySModel.dispose();
      mySModel=null;
    }
    mySModel=newModel.getModelData();
    if (mySModel != null) {
      mySModel.setModelDescriptor(this,getNodeEventDispatch());
    }
    setLoadingState(newModel.getState());
  }
  fireModelStateChanged(oldState,getLoadingState());
  fireModelReplaced();
}

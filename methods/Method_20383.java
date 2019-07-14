private boolean areModelsWaitingToWrite(){
  return dataBindingProcessor.hasModelsToWrite() || modelProcessor.hasModelsToWrite() || modelViewProcessor.hasModelsToWrite();
}

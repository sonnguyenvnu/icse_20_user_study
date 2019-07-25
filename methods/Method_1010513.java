void dispose(){
  LOG.debug("Disposing " + this);
  new ModuleRepositoryFacade(myRepository).unregisterModules(this);
  myFile.removeListener(myPostNotifyDispatch);
}

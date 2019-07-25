void attach(){
  LOG.debug("Attaching " + this);
  myFile.addListener(myPostNotifyDispatch);
  collectAndRegisterModules();
}

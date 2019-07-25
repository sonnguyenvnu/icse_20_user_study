public void init(){
  File cFile=configuration.getCredFile();
  if (cFile != null) {
    try {
      cred.load(cFile);
      cred.setFile(cFile);
    }
 catch (    ConfigurationException e) {
      LOGGER.warn("Can't load credentials file {}: {}",cFile,e.getMessage());
      LOGGER.trace("",e);
    }
  }
 else {
    LOGGER.debug("Something went seriously wrong - getCredFile() returned null!");
  }
  refreshCred(credTable);
}

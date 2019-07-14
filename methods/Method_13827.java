private void readModuleProperty(){
  File f=getPath();
  if (logger.isDebugEnabled()) {
    logger.debug("Module getPath(): {}",f.getPath());
  }
  File modFile=new File(f,"MOD-INF");
  if (logger.isDebugEnabled()) {
    logger.debug("Module File: {}",modFile.getPath());
  }
  if (modFile.exists()) {
    extensionProperties=loadProperties(new File(modFile,"dbextension.properties"));
  }
}

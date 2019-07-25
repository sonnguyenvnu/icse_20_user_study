private void warn(short actualSaFamily,short expectedSaFamily,String devName,String field){
  if (logger.isWarnEnabled()) {
    logger.warn("Couldn't analyze an address. " + "devName: {}, field: {}, actual saFamily: {}, expected saFamily: {}",devName,field,actualSaFamily,expectedSaFamily);
  }
}

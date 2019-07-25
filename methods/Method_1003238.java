/** 
 * Save the lock file.
 * @return the saved properties
 */
public Properties save(){
  try {
    try (OutputStream out=FileUtils.newOutputStream(fileName,false)){
      properties.store(out,MAGIC);
    }
     lastWrite=aggressiveLastModified(fileName);
    if (trace.isDebugEnabled()) {
      trace.debug("save " + properties);
    }
    return properties;
  }
 catch (  IOException e) {
    throw getExceptionFatal("Could not save properties " + fileName,e);
  }
}

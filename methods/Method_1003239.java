/** 
 * Load the properties file.
 * @return the properties
 */
public Properties load(){
  IOException lastException=null;
  for (int i=0; i < 5; i++) {
    try {
      Properties p2=SortedProperties.loadProperties(fileName);
      if (trace.isDebugEnabled()) {
        trace.debug("load " + p2);
      }
      return p2;
    }
 catch (    IOException e) {
      lastException=e;
    }
  }
  throw getExceptionFatal("Could not load properties " + fileName,lastException);
}

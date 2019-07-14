@SuppressWarnings({"unchecked","rawtypes"}) private static void loadMimeTypes(Map<String,String> result,String resourceName){
  try {
    Enumeration<URL> resources=NanoHTTPD.class.getClassLoader().getResources(resourceName);
    while (resources.hasMoreElements()) {
      URL url=(URL)resources.nextElement();
      Properties properties=new Properties();
      InputStream stream=null;
      try {
        stream=url.openStream();
        properties.load(stream);
      }
 catch (      IOException e) {
        LOG.log(Level.SEVERE,"could not load mimetypes from " + url,e);
      }
 finally {
        safeClose(stream);
      }
      result.putAll((Map)properties);
    }
  }
 catch (  IOException e) {
    LOG.log(Level.INFO,"no mime types available at " + resourceName);
  }
}

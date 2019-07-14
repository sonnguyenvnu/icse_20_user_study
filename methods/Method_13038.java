protected String getJdCoreVersion(){
  try {
    Enumeration<URL> enumeration=ConfigurationXmlPersisterProvider.class.getClassLoader().getResources("META-INF/MANIFEST.MF");
    while (enumeration.hasMoreElements()) {
      try (InputStream is=enumeration.nextElement().openStream()){
        String attribute=new Manifest(is).getMainAttributes().getValue("JD-Core-Version");
        if (attribute != null) {
          return attribute;
        }
      }
     }
  }
 catch (  IOException e) {
    assert ExceptionUtil.printStackTrace(e);
  }
  return "SNAPSHOT";
}

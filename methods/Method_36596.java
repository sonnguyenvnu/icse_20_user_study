private static String determineSofaBootVersion(){
  URL codeSourceLocation=Marker.class.getProtectionDomain().getCodeSource().getLocation();
  try {
    URLConnection connection=codeSourceLocation.openConnection();
    if (connection instanceof JarURLConnection) {
      return getImplementationVersion(((JarURLConnection)connection).getJarFile());
    }
    try (JarFile jarFile=new JarFile(new File(codeSourceLocation.toURI()))){
      return getImplementationVersion(jarFile);
    }
   }
 catch (  Exception ex) {
    return null;
  }
}

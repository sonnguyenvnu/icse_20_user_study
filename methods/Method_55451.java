/** 
 * Returns the value of the specified manifest attribute in the LWJGL JAR file.
 * @param attributeName the attribute name
 * @return the attribute value or null if the attribute was not found or there is no LWJGL JAR file
 */
public static Optional<String> apiGetManifestValue(String attributeName){
  URL url=APIUtil.class.getClassLoader().getResource("org/lwjgl/system/APIUtil.class");
  if (url != null) {
    String classURL=url.toString();
    if (classURL.startsWith("jar:")) {
      try (InputStream stream=new URL(classURL.substring(0,classURL.lastIndexOf('!') + 1) + '/' + JarFile.MANIFEST_NAME).openStream()){
        return Optional.ofNullable(new Manifest(stream).getMainAttributes().getValue(attributeName));
      }
 catch (      Exception e) {
        e.printStackTrace(DEBUG_STREAM);
      }
    }
  }
  return Optional.empty();
}

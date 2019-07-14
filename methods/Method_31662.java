/** 
 * Retrieves the Jar file represented by this URL.
 * @param locationUrl The URL of the jar.
 * @return The jar file.
 * @throws IOException when the jar could not be resolved.
 */
private JarFile getJarFromUrl(URL locationUrl) throws IOException {
  URLConnection con=locationUrl.openConnection();
  if (con instanceof JarURLConnection) {
    JarURLConnection jarCon=(JarURLConnection)con;
    jarCon.setUseCaches(false);
    return jarCon.getJarFile();
  }
  String urlFile=locationUrl.getFile();
  int separatorIndex=urlFile.indexOf(separator);
  if (separatorIndex != -1) {
    String jarFileUrl=urlFile.substring(0,separatorIndex);
    if (jarFileUrl.startsWith("file:")) {
      try {
        return new JarFile(new URL(jarFileUrl).toURI().getSchemeSpecificPart());
      }
 catch (      URISyntaxException ex) {
        return new JarFile(jarFileUrl.substring("file:".length()));
      }
    }
    return new JarFile(jarFileUrl);
  }
  return new JarFile(urlFile);
}

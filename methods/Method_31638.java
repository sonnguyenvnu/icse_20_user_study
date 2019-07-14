/** 
 * @return The url of this resource.
 */
private URL getUrl(){
  return classLoader.getResource(fileNameWithAbsolutePath);
}

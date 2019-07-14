/** 
 * Converts this resource name to a fully qualified class name.
 * @param resourceName The resource name.
 * @return The class name.
 */
private String toClassName(String resourceName){
  String nameWithDots=resourceName.replace("/",".");
  return nameWithDots.substring(0,(nameWithDots.length() - ".class".length()));
}

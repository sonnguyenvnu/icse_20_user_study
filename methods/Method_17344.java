/** 
 * Returns a sanatized string for use as a management bean name. 
 */
private static String sanitize(String name){
  return (name == null) ? "" : name.replaceAll(",|:|=|\n",".");
}

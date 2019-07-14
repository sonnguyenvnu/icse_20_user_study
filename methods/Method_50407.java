/** 
 * Build file path string.
 * @param applicationName the application name
 * @return the string
 */
public static String buildFilePath(final String applicationName){
  return String.join("/",CommonConstant.PATH_SUFFIX,applicationName.replaceAll("-","_"));
}

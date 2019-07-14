/** 
 * Retrieves the physical location on disk of this class.
 * @param aClass The class to get the location for.
 * @return The absolute path of the .class file.
 */
public static String getLocationOnDisk(Class<?> aClass){
  ProtectionDomain protectionDomain=aClass.getProtectionDomain();
  if (protectionDomain == null) {
    return null;
  }
  CodeSource codeSource=protectionDomain.getCodeSource();
  if (codeSource == null) {
    return null;
  }
  return UrlUtils.decodeURL(codeSource.getLocation().getPath());
}

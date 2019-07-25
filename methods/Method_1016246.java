/** 
 * Initializes VersionManager settings.
 */
public static synchronized void initialize(){
  if (!initialized) {
    initialized=true;
    XmlUtils.processAnnotations(VersionInfo.class);
  }
}

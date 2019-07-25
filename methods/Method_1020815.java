/** 
 * Tries to detect if the current system supports ANSI.
 */
private static boolean detect(){
  if (System.getProperty("jline.enabled","false").equalsIgnoreCase("false")) {
    return false;
  }
  boolean enabled=Terminal.getTerminal().isANSISupported();
  if (!enabled) {
    String force=System.getProperty(ANSI.class.getName() + ".force","false");
    enabled=Boolean.valueOf(force).booleanValue();
  }
  return enabled;
}

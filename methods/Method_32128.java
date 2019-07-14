/** 
 * Checks whether the provider may be changed using permission 'CurrentTime.setProvider'.
 * @throws SecurityException if the provider may not be changed
 */
private static void checkPermission() throws SecurityException {
  SecurityManager sm=System.getSecurityManager();
  if (sm != null) {
    sm.checkPermission(new JodaTimePermission("CurrentTime.setProvider"));
  }
}

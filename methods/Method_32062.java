/** 
 * Checks whether the user has permission 'ConverterManager.alterIntervalConverters'.
 * @throws SecurityException if the user does not have the permission
 */
private void checkAlterIntervalConverters() throws SecurityException {
  SecurityManager sm=System.getSecurityManager();
  if (sm != null) {
    sm.checkPermission(new JodaTimePermission("ConverterManager.alterIntervalConverters"));
  }
}

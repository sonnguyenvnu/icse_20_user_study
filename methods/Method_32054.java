/** 
 * Checks whether the user has permission 'ConverterManager.alterPartialConverters'.
 * @throws SecurityException if the user does not have the permission
 */
private void checkAlterPartialConverters() throws SecurityException {
  SecurityManager sm=System.getSecurityManager();
  if (sm != null) {
    sm.checkPermission(new JodaTimePermission("ConverterManager.alterPartialConverters"));
  }
}

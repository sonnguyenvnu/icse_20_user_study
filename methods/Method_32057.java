/** 
 * Checks whether the user has permission 'ConverterManager.alterDurationConverters'.
 * @throws SecurityException if the user does not have the permission
 */
private void checkAlterDurationConverters() throws SecurityException {
  SecurityManager sm=System.getSecurityManager();
  if (sm != null) {
    sm.checkPermission(new JodaTimePermission("ConverterManager.alterDurationConverters"));
  }
}

/** 
 * Checks whether the user has permission 'ConverterManager.alterInstantConverters'.
 * @throws SecurityException if the user does not have the permission
 */
private void checkAlterInstantConverters() throws SecurityException {
  SecurityManager sm=System.getSecurityManager();
  if (sm != null) {
    sm.checkPermission(new JodaTimePermission("ConverterManager.alterInstantConverters"));
  }
}

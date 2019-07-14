/** 
 * Checks whether the user has permission 'ConverterManager.alterPeriodConverters'.
 * @throws SecurityException if the user does not have the permission
 */
private void checkAlterPeriodConverters() throws SecurityException {
  SecurityManager sm=System.getSecurityManager();
  if (sm != null) {
    sm.checkPermission(new JodaTimePermission("ConverterManager.alterPeriodConverters"));
  }
}

/** 
 * Removes previously installed SLF4JBridgeHandler instances. See also {@link #install()}.
 * @throws SecurityException A <code>SecurityException</code> is thrown, if a security managerexists and if the caller does not have LoggingPermission("control").
 */
public static void uninstall() throws SecurityException {
  java.util.logging.Logger rootLogger=getRootLogger();
  Handler[] handlers=rootLogger.getHandlers();
  for (int i=0; i < handlers.length; i++) {
    if (handlers[i] instanceof SLF4JBridgeHandler) {
      rootLogger.removeHandler(handlers[i]);
    }
  }
}

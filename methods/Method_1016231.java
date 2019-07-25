/** 
 * Initializes manager if it wasn't already initialized.
 */
public static synchronized void initialize(){
  if (!initialized) {
    initialized=true;
    ProxyManager.setAuthenticator(new WebProxyAuthenticator());
    ProxyManager.setSystemProxyConfirmationSupport(new WebSystemProxyConfirmationSupport());
    ProxyManager.initialize();
  }
}

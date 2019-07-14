/** 
 * <p> Set the JDBC driver delegate class. </p>
 * @param delegateClassName the delegate class name
 */
@SuppressWarnings("UnusedDeclaration") public void setDriverDelegateClass(String delegateClassName) throws InvalidConfigurationException {
synchronized (this) {
    this.delegateClassName=delegateClassName;
  }
}

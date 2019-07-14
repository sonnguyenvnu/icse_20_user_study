/** 
 * Create a new instance of this connection manager.
 * @return an instance of the manager
 * @throws DatabaseServiceException
 */
public static MariaDBConnectionManager getInstance() throws DatabaseServiceException {
  if (instance == null) {
    instance=new MariaDBConnectionManager();
  }
  return instance;
}

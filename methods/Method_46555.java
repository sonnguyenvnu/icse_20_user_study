/** 
 * This method should only be used in test scenarios
 * @param connection the underlying connection (possibly a mock)
 * @return a new {@link ConnectionInformation} instance
 */
public static ConnectionInformation fromConnection(Connection connection){
  final ConnectionInformation connectionInformation=new ConnectionInformation();
  connectionInformation.connection=connection;
  return connectionInformation;
}

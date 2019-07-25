/** 
 * Implement Ranger Authentication Service. Initiate RangerClient and RangerClientConfig for initializing service and invoke different methods of it.
 */
@Override public void initialize(AuthorizationConfiguration config){
  rangerConnection=(RangerConnection)config;
  RangerRestClientConfig rangerClientConfiguration=new RangerRestClientConfig(rangerConnection.getHostName(),rangerConnection.getUsername(),rangerConnection.getPassword());
  rangerClientConfiguration.setPort(rangerConnection.getPort());
  rangerRestClient=new RangerRestClient(rangerClientConfiguration);
}

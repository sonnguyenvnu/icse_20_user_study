@Override protected ClientDetailsService performBuild(){
  Assert.state(dataSource != null,"You need to provide a DataSource");
  JdbcClientDetailsService clientDetailsService=new JdbcClientDetailsService(dataSource);
  if (passwordEncoder != null) {
    clientDetailsService.setPasswordEncoder(passwordEncoder);
  }
  for (  ClientDetails client : clientDetails) {
    clientDetailsService.addClientDetails(client);
  }
  return clientDetailsService;
}

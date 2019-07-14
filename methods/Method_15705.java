@Bean public DefaultAuthorizingHandler authorizingHandler(DataAccessController dataAccessController){
  return new DefaultAuthorizingHandler(dataAccessController);
}

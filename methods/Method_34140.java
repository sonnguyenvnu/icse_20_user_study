@PostConstruct public void init(){
  for (  AuthorizationServerConfigurer configurer : configurers) {
    try {
      configurer.configure(endpoints);
    }
 catch (    Exception e) {
      throw new IllegalStateException("Cannot configure enpdoints",e);
    }
  }
  endpoints.setClientDetailsService(clientDetailsService);
}

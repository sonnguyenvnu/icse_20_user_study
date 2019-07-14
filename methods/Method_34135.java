@SuppressWarnings("rawtypes") public ClientDetailsServiceBuilder<?> clients(final ClientDetailsService clientDetailsService) throws Exception {
  return new ClientDetailsServiceBuilder(){
    @Override public ClientDetailsService build() throws Exception {
      return clientDetailsService;
    }
  }
;
}

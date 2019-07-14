@Override protected ProtectedResourceDetailsService createInstance() throws Exception {
  Map<String,ProtectedResourceDetails> detailsMap=BeanFactoryUtils.beansOfTypeIncludingAncestors((ListableBeanFactory)getBeanFactory(),ProtectedResourceDetails.class);
  InMemoryProtectedResourceDetailsService service=new InMemoryProtectedResourceDetailsService();
  service.setResourceDetailsStore(detailsMap);
  return service;
}

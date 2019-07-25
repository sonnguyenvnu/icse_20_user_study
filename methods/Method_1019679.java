private synchronized void load(){
  if (singletons != null) {
    return;
  }
  HashSet<Object> singletons=new HashSet<Object>();
  singletons.addAll(Providers.get());
  List<JAXRSResourcesService> services=getRegistry().getJaxRsResourcesService();
  for (  final ResourcesService service : services) {
    singletons.add(new JAXRSResourcesServiceImpl(service));
  }
  this.singletons=singletons;
}

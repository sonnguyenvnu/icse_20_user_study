@Override public Set<ServiceRestMetadata> resolveServiceRestMetadata(ServiceBean serviceBean){
  Object bean=serviceBean.getRef();
  Class<?> beanType=bean.getClass();
  Set<ServiceRestMetadata> serviceRestMetadata=new LinkedHashSet<>();
  Set<RestMethodMetadata> methodRestMetadata=resolveMethodRestMetadata(beanType);
  List<URL> urls=serviceBean.getExportedUrls();
  urls.stream().map(URL::toString).forEach(url -> {
    ServiceRestMetadata metadata=new ServiceRestMetadata();
    metadata.setUrl(url);
    metadata.setMeta(methodRestMetadata);
    serviceRestMetadata.add(metadata);
  }
);
  return serviceRestMetadata;
}

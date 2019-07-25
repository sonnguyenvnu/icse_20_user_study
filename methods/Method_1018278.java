@Override public boolean match(ServiceReference serviceReference){
  AssertUtils.assertNotNull(serviceReference,"ServiceReference should not be null");
  ServiceMetadata serviceMetadata=serviceReference.getServiceMetadata();
  ServiceProvider provider=serviceMetadata.getServiceProvider();
  boolean isMatch=matchProviderType(provider.getServiceProviderType());
  isMatch&=matchServiceInterface(serviceMetadata.getInterfaceClass());
  isMatch&=matchUniqueId(serviceMetadata.getUniqueId());
  return isMatch;
}

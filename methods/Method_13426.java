private void initSubscribedDubboMetadataServiceURLs(URL dubboMetadataServiceURL){
  String serviceKey=dubboMetadataServiceURL.getServiceKey();
  subscribedDubboMetadataServiceURLs.add(serviceKey,dubboMetadataServiceURL);
}

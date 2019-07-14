protected boolean isDubboMetadataServiceURL(URL url){
  return DUBBO_METADATA_SERVICE_CLASS_NAME.equals(url.getServiceInterface());
}

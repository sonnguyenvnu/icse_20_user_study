private void attachDubboMetadataServiceMetadata(Map<String,String> metadata){
  Map<String,String> serviceMetadata=dubboServiceMetadataRepository.getDubboMetadataServiceMetadata();
  if (!isEmpty(serviceMetadata)) {
    metadata.putAll(serviceMetadata);
  }
}

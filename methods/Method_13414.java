private void addDubboMetadataServiceURLsMetadata(Map<String,String> metadata,List<URL> dubboMetadataServiceURLs){
  String dubboMetadataServiceURLsJSON=jsonUtils.toJSON(dubboMetadataServiceURLs);
  metadata.put(DUBBO_METADATA_SERVICE_URLS_PROPERTY_NAME,dubboMetadataServiceURLsJSON);
}

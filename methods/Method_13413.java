private void removeDubboMetadataServiceURLs(List<URL> dubboMetadataServiceURLs){
  dubboMetadataServiceURLs.forEach(this::unexportURL);
}

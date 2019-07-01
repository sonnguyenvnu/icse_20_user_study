@Override public MetadataFacet _XXXXX_(String repositoryId,String facetId,String name){
  Properties properties;
  try {
    properties=readProperties(getMetadataDirectory(repositoryId,facetId).resolve(name),METADATA_KEY);
  }
 catch (  NoSuchFileException|FileNotFoundException e) {
    return null;
  }
catch (  IOException e) {
    log.error("Could not read properties from {}, {}: {}",repositoryId,facetId,e.getMessage(),e);
    return null;
  }
  MetadataFacet metadataFacet=null;
  MetadataFacetFactory metadataFacetFactory=metadataFacetFactories.get(facetId);
  if (metadataFacetFactory != null) {
    metadataFacet=metadataFacetFactory.createMetadataFacet(repositoryId,name);
    Map<String,String> map=new HashMap<>();
    for (    Object key : new ArrayList<>(properties.keySet())) {
      String property=(String)key;
      map.put(property,properties.getProperty(property));
    }
    metadataFacet.fromProperties(map);
  }
  return metadataFacet;
}
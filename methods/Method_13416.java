/** 
 * Publish the  {@link Set} of {@link ServiceRestMetadata}
 * @param serviceRestMetadataSet the {@link Set} of {@link ServiceRestMetadata}
 */
public void publishServiceRestMetadata(Set<ServiceRestMetadata> serviceRestMetadataSet){
  for (  ServiceRestMetadata serviceRestMetadata : serviceRestMetadataSet) {
    if (!isEmpty(serviceRestMetadata.getMeta())) {
      this.serviceRestMetadata.add(serviceRestMetadata);
    }
  }
}

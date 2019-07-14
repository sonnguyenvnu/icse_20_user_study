private boolean isInstanceAllowedBasedOnMetadata(ServiceInstance instance){
  if (instancesMetadata.isEmpty()) {
    return true;
  }
  for (  Map.Entry<String,String> metadata : instance.getMetadata().entrySet()) {
    if (isMapContainsEntry(instancesMetadata,metadata)) {
      return true;
    }
  }
  return false;
}

@ReadOperation public List<Object> versions(){
  if (endpointResult.isEmpty()) {
    try {
      endpointResult=Stream.of(resourcePatternResolver.getResources(SOFA_BOOT_VERSION_PROPERTIES)).map(this::loadProperties).collect(Collectors.toList());
    }
 catch (    Exception ex) {
      logger.warn("Load properties failed: {}",ex.getMessage());
    }
  }
  return endpointResult;
}

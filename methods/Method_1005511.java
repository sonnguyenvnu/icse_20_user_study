boolean resolve(Input input){
  final String securedLabelValue=input.getServiceLabels().getOrDefault("secured","false");
  if (TRUTHY_STRINGS.contains(securedLabelValue)) {
    if (log.isDebugEnabled()) {
      log.debug("Considering service with name: " + input.getServiceName() + " and port " + input.getPort() + " is secure since the service contains a true value for the 'secured' label");
    }
    return true;
  }
  final String securedAnnotationValue=input.getServiceAnnotations().getOrDefault("secured","false");
  if (TRUTHY_STRINGS.contains(securedAnnotationValue)) {
    if (log.isDebugEnabled()) {
      log.debug("Considering service with name: " + input.getServiceName() + " and port " + input.getPort() + " is secure since the service contains a true value for the 'secured' annotation");
    }
    return true;
  }
  if (input.getPort() != null && this.properties.getKnownSecurePorts().contains(input.getPort())) {
    if (log.isDebugEnabled()) {
      log.debug("Considering service with name: " + input.getServiceName() + " and port " + input.getPort() + " is secure due to the port being a known https port");
    }
    return true;
  }
  return false;
}

public String generate(Service service) throws DescriptorBindingException {
  try {
    log.fine("Generating XML descriptor from service model: " + service);
    return XMLUtil.documentToString(buildDOM(service));
  }
 catch (  Exception ex) {
    throw new DescriptorBindingException("Could not build DOM: " + ex.getMessage(),ex);
  }
}

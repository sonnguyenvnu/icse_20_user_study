public String generate(Device deviceModel,RemoteClientInfo info,Namespace namespace) throws DescriptorBindingException {
  try {
    log.fine("Generating XML descriptor from device model: " + deviceModel);
    return XMLUtil.documentToString(buildDOM(deviceModel,info,namespace));
  }
 catch (  Exception ex) {
    throw new DescriptorBindingException("Could not build DOM: " + ex.getMessage(),ex);
  }
}

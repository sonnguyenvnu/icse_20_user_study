public <D extends Device>D describe(D undescribedDevice,String descriptorXml) throws DescriptorBindingException, ValidationException {
  if (descriptorXml == null || descriptorXml.length() == 0) {
    throw new DescriptorBindingException("Null or empty descriptor");
  }
  try {
    log.fine("Populating device from XML descriptor: " + undescribedDevice);
    DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
    factory.setNamespaceAware(true);
    DocumentBuilder documentBuilder=factory.newDocumentBuilder();
    documentBuilder.setErrorHandler(this);
    Document d=documentBuilder.parse(new InputSource(new StringReader(descriptorXml.trim())));
    return describe(undescribedDevice,d);
  }
 catch (  ValidationException ex) {
    throw ex;
  }
catch (  Exception ex) {
    throw new DescriptorBindingException("Could not parse device descriptor: " + ex.toString(),ex);
  }
}

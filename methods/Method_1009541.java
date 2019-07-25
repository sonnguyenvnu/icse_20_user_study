public <S extends Service>S describe(S undescribedService,String descriptorXml) throws DescriptorBindingException, ValidationException {
  if (descriptorXml == null || descriptorXml.length() == 0) {
    throw new DescriptorBindingException("Null or empty descriptor");
  }
  try {
    log.fine("Populating service from XML descriptor: " + undescribedService);
    DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
    factory.setNamespaceAware(true);
    DocumentBuilder documentBuilder=factory.newDocumentBuilder();
    documentBuilder.setErrorHandler(this);
    Document d=documentBuilder.parse(new InputSource(new StringReader(descriptorXml.trim())));
    return describe(undescribedService,d);
  }
 catch (  ValidationException ex) {
    throw ex;
  }
catch (  Exception ex) {
    throw new DescriptorBindingException("Could not parse service descriptor: " + ex.toString(),ex);
  }
}

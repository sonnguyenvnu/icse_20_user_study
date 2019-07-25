public void marshal(org.w3c.dom.Node node) throws JAXBException {
  try {
    Marshaller marshaller=Context.jcContentTypes.createMarshaller();
    NamespacePrefixMapperUtils.setProperty(marshaller,NamespacePrefixMapperUtils.getPrefixMapper());
    log.debug("marshalling " + this.getClass().getName() + " ...");
    marshaller.marshal(buildTypes(),node);
    log.debug("content types marshalled \n\n");
  }
 catch (  JAXBException e) {
    log.error(e.getMessage(),e);
    throw e;
  }
}

private Marshaller createMarshaller() throws JAXBException {
  Marshaller marshaller=context.createMarshaller();
  marshaller.setProperty("jaxb.fragment",Boolean.TRUE);
  return marshaller;
}

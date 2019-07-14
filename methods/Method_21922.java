@Override public synchronized void save(final E entity){
  try {
    Marshaller marshaller=jaxbContext.createMarshaller();
    marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
    marshaller.marshal(entity,file);
  }
 catch (  final JAXBException ex) {
    throw new JobConsoleException(ex);
  }
}

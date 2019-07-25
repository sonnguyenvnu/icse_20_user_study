public void marshal(OutputStream os) throws Docx4JException {
  if (pkgResult == null) {
    if (packageIn == null) {
      throw new Docx4JException("No zipped package to convert to Flat OPC Package");
    }
 else {
      get();
    }
  }
  try {
    JAXBContext jc=Context.jcXmlPackage;
    Marshaller marshaller=jc.createMarshaller();
    marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
    NamespacePrefixMapperUtils.setProperty(marshaller,NamespacePrefixMapperUtils.getPrefixMapper());
    marshaller.marshal(pkgResult,os);
  }
 catch (  JAXBException e) {
    throw new Docx4JException("Couldn't marshall Flat OPC Package",e);
  }
}

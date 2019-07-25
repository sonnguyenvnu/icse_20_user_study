public static EncryptMessageHandler parser(String xmlContent) throws RuntimeException {
  try {
    XMLReader xmlReader=XMLReaderFactory.createXMLReader();
    xmlReader.setContentHandler(global);
    xmlReader.parse(new InputSource(new ByteArrayInputStream(xmlContent.getBytes(ServerToolkits.UTF_8))));
  }
 catch (  IOException e) {
    throw new RuntimeException(e);
  }
catch (  SAXException e) {
    throw new RuntimeException(e);
  }
  return global;
}

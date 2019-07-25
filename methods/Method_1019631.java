public static Document load(String xml) throws ParserConfigurationException, SAXException, IOException {
  DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
  factory.setNamespaceAware(true);
  DocumentBuilder builder=factory.newDocumentBuilder();
  return builder.parse(IOUtils.toInputStream(xml,EncodingConstants.UTF_8.name()));
}

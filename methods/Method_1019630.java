public static Document load(InputStream stream) throws ParserConfigurationException, SAXException, IOException {
  DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
  factory.setNamespaceAware(true);
  DocumentBuilder builder=factory.newDocumentBuilder();
  return builder.parse(stream);
}

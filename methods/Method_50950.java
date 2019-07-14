private Document createDocument(){
  try {
    DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
    DocumentBuilder parser=factory.newDocumentBuilder();
    return parser.newDocument();
  }
 catch (  ParserConfigurationException e) {
    throw new IllegalStateException(e);
  }
}

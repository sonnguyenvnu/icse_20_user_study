private Document getDocument(String xml){
  try {
    DocumentBuilder parser=DocumentBuilderFactory.newInstance().newDocumentBuilder();
    return parser.parse(new InputSource(new StringReader(xml)));
  }
 catch (  ParserConfigurationException|SAXException|IOException e) {
    e.printStackTrace();
  }
  return null;
}

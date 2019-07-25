public static void main(String argv[]) throws ParserConfigurationException, SAXException, IOException {
  ConfigurationParser parser=new ConfigurationParser();
  parser.parseFile(new File("config.xml"));
  parser.getShapeDefinitions();
}

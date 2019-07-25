/** 
 * Indents the given XML String.
 * @param r A reader on XML data
 * @param w A writer for the indented XML
 * @throws IOException If an IOException occurs.
 * @throws SAXException If the XML is not well-formed.
 * @throws ParserConfigurationException If the parser could not be configured
 */
public static void indent(Reader r,Writer w) throws SAXException, IOException, ParserConfigurationException {
  XMLIndenter indenter=new XMLIndenter(w);
  SAXParserFactory factory=SAXParserFactory.newInstance();
  factory.setNamespaceAware(false);
  factory.setValidating(false);
  InputSource source=new InputSource(r);
  SAXParser parser=factory.newSAXParser();
  parser.setProperty("http://xml.org/sax/features/external-general-entities",false);
  parser.setProperty("http://xml.org/sax/features/external-parameter-entities",false);
  parser.setProperty("http://apache.org/xml/features/disallow-doctype-decl",true);
  XMLReader xmlreader=parser.getXMLReader();
  xmlreader.setContentHandler(indenter);
  xmlreader.parse(source);
}

/** 
 * Indents the given XML String.
 * @param xml The XML string to indent
 * @return The indented XML String.
 * @throws IOException If an IOException occurs.
 * @throws SAXException If the XML is not well-formed.
 * @throws ParserConfigurationException If the parser could not be configured
 */
public static String indent(String xml) throws SAXException, IOException, ParserConfigurationException {
  Writer writer=new StringWriter();
  Reader reader=new StringReader(xml);
  indent(reader,writer);
  return writer.toString();
}

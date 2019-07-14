/** 
 * Parses a Role element.
 * @param xpp The parser from which to read.
 * @throws XmlPullParserException If an error occurs parsing the element.
 * @throws IOException If an error occurs reading the element.
 * @return {@link C.SelectionFlags} parsed from the element.
 */
protected int parseRole(XmlPullParser xpp) throws XmlPullParserException, IOException {
  String schemeIdUri=parseString(xpp,"schemeIdUri",null);
  String value=parseString(xpp,"value",null);
  do {
    xpp.next();
  }
 while (!XmlPullParserUtil.isEndTag(xpp,"Role"));
  return "urn:mpeg:dash:role:2011".equals(schemeIdUri) && "main".equals(value) ? C.SELECTION_FLAG_DEFAULT : 0;
}

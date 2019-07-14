/** 
 * Parses children of AdaptationSet elements not specifically parsed elsewhere.
 * @param xpp The XmpPullParser from which the AdaptationSet child should be parsed.
 * @throws XmlPullParserException If an error occurs parsing the element.
 * @throws IOException If an error occurs reading the element.
 */
protected void parseAdaptationSetChild(XmlPullParser xpp) throws XmlPullParserException, IOException {
  maybeSkipTag(xpp);
}

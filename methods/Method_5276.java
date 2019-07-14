protected static String parseBaseUrl(XmlPullParser xpp,String parentBaseUrl) throws XmlPullParserException, IOException {
  xpp.next();
  return UriUtil.resolve(parentBaseUrl,xpp.getText());
}

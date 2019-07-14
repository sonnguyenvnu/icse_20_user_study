private void parseMetadata(XmlPullParser xmlParser,Map<String,String> imageMap) throws IOException, XmlPullParserException {
  do {
    xmlParser.next();
    if (XmlPullParserUtil.isStartTag(xmlParser,TtmlNode.TAG_IMAGE)) {
      String id=XmlPullParserUtil.getAttributeValue(xmlParser,"id");
      if (id != null) {
        String encodedBitmapData=xmlParser.nextText();
        imageMap.put(id,encodedBitmapData);
      }
    }
  }
 while (!XmlPullParserUtil.isEndTag(xmlParser,TtmlNode.TAG_METADATA));
}

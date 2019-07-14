private Map<String,TtmlStyle> parseHeader(XmlPullParser xmlParser,Map<String,TtmlStyle> globalStyles,CellResolution cellResolution,TtsExtent ttsExtent,Map<String,TtmlRegion> globalRegions,Map<String,String> imageMap) throws IOException, XmlPullParserException {
  do {
    xmlParser.next();
    if (XmlPullParserUtil.isStartTag(xmlParser,TtmlNode.TAG_STYLE)) {
      String parentStyleId=XmlPullParserUtil.getAttributeValue(xmlParser,ATTR_STYLE);
      TtmlStyle style=parseStyleAttributes(xmlParser,new TtmlStyle());
      if (parentStyleId != null) {
        for (        String id : parseStyleIds(parentStyleId)) {
          style.chain(globalStyles.get(id));
        }
      }
      if (style.getId() != null) {
        globalStyles.put(style.getId(),style);
      }
    }
 else     if (XmlPullParserUtil.isStartTag(xmlParser,TtmlNode.TAG_REGION)) {
      TtmlRegion ttmlRegion=parseRegionAttributes(xmlParser,cellResolution,ttsExtent);
      if (ttmlRegion != null) {
        globalRegions.put(ttmlRegion.id,ttmlRegion);
      }
    }
 else     if (XmlPullParserUtil.isStartTag(xmlParser,TtmlNode.TAG_METADATA)) {
      parseMetadata(xmlParser,imageMap);
    }
  }
 while (!XmlPullParserUtil.isEndTag(xmlParser,TtmlNode.TAG_HEAD));
  return globalStyles;
}

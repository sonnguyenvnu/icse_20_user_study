protected UtcTimingElement parseUtcTiming(XmlPullParser xpp){
  String schemeIdUri=xpp.getAttributeValue(null,"schemeIdUri");
  String value=xpp.getAttributeValue(null,"value");
  return buildUtcTimingElement(schemeIdUri,value);
}

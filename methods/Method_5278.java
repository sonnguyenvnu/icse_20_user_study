protected static String parseString(XmlPullParser xpp,String name,String defaultValue){
  String value=xpp.getAttributeValue(null,name);
  return value == null ? defaultValue : value;
}

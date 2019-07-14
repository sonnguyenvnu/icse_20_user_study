protected static int parseInt(XmlPullParser xpp,String name,int defaultValue){
  String value=xpp.getAttributeValue(null,name);
  return value == null ? defaultValue : Integer.parseInt(value);
}

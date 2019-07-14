private static String xmlParserEventString(int event){
switch (event) {
case XmlPullParser.START_DOCUMENT:
    return "START_DOCUMENT";
case XmlPullParser.END_DOCUMENT:
  return "END_DOCUMENT";
case XmlPullParser.START_TAG:
return "START_TAG";
case XmlPullParser.END_TAG:
return "END_TAG";
case XmlPullParser.TEXT:
return "TEXT";
}
return Integer.toString(event);
}

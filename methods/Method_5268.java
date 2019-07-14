protected RangedUri parseRangedUrl(XmlPullParser xpp,String urlAttribute,String rangeAttribute){
  String urlText=xpp.getAttributeValue(null,urlAttribute);
  long rangeStart=0;
  long rangeLength=C.LENGTH_UNSET;
  String rangeText=xpp.getAttributeValue(null,rangeAttribute);
  if (rangeText != null) {
    String[] rangeTextArray=rangeText.split("-");
    rangeStart=Long.parseLong(rangeTextArray[0]);
    if (rangeTextArray.length == 2) {
      rangeLength=Long.parseLong(rangeTextArray[1]) - rangeStart + 1;
    }
  }
  return buildRangedUri(urlText,rangeStart,rangeLength);
}

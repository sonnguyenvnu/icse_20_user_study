protected SingleSegmentBase parseSegmentBase(XmlPullParser xpp,SingleSegmentBase parent) throws XmlPullParserException, IOException {
  long timescale=parseLong(xpp,"timescale",parent != null ? parent.timescale : 1);
  long presentationTimeOffset=parseLong(xpp,"presentationTimeOffset",parent != null ? parent.presentationTimeOffset : 0);
  long indexStart=parent != null ? parent.indexStart : 0;
  long indexLength=parent != null ? parent.indexLength : 0;
  String indexRangeText=xpp.getAttributeValue(null,"indexRange");
  if (indexRangeText != null) {
    String[] indexRange=indexRangeText.split("-");
    indexStart=Long.parseLong(indexRange[0]);
    indexLength=Long.parseLong(indexRange[1]) - indexStart + 1;
  }
  RangedUri initialization=parent != null ? parent.initialization : null;
  do {
    xpp.next();
    if (XmlPullParserUtil.isStartTag(xpp,"Initialization")) {
      initialization=parseInitialization(xpp);
    }
 else {
      maybeSkipTag(xpp);
    }
  }
 while (!XmlPullParserUtil.isEndTag(xpp,"SegmentBase"));
  return buildSingleSegmentBase(initialization,timescale,presentationTimeOffset,indexStart,indexLength);
}

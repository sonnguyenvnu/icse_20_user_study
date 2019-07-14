/** 
 * @return The debugging information of the parser's current position
 */
private String xmlParserDebugContext(){
  mLogStringBuilder.setLength(0);
  if (mInputParser != null) {
    try {
      final int eventType=mInputParser.getEventType();
      mLogStringBuilder.append(xmlParserEventString(eventType));
      if (eventType == XmlPullParser.START_TAG || eventType == XmlPullParser.END_TAG || eventType == XmlPullParser.TEXT) {
        mLogStringBuilder.append('<').append(mInputParser.getName());
        for (int i=0; i < mInputParser.getAttributeCount(); i++) {
          mLogStringBuilder.append(' ').append(mInputParser.getAttributeName(i)).append('=').append(mInputParser.getAttributeValue(i));
        }
        mLogStringBuilder.append("/>");
      }
      return mLogStringBuilder.toString();
    }
 catch (    XmlPullParserException e) {
      Timber.e(e,"xmlParserDebugContext: " + e);
    }
  }
  return "Unknown";
}

/** 
 * Move XML parser forward to next event type or the end of doc
 * @param eventType
 * @return The final event type we meet
 * @throws XmlPullParserException
 * @throws IOException
 */
private int advanceToNextEvent(int eventType) throws XmlPullParserException, IOException {
  for (; ; ) {
    int nextEvent=mInputParser.next();
    if (nextEvent == eventType || nextEvent == XmlPullParser.END_DOCUMENT) {
      return nextEvent;
    }
  }
}

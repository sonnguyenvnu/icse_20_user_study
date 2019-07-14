/** 
 * Returns the value of an attribute of the current start tag. Any raw attribute names in the current start tag have their prefixes stripped before matching.
 * @param xpp The {@link XmlPullParser} to query.
 * @param attributeName The name of the attribute.
 * @return The value of the attribute, or null if the current event is not a start tag or if nosuch attribute was found.
 */
public static @Nullable String getAttributeValueIgnorePrefix(XmlPullParser xpp,String attributeName){
  int attributeCount=xpp.getAttributeCount();
  for (int i=0; i < attributeCount; i++) {
    if (stripPrefix(xpp.getAttributeName(i)).equals(attributeName)) {
      return xpp.getAttributeValue(i);
    }
  }
  return null;
}

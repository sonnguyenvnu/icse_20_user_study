/** 
 * Determine the content type of the given message. This method will try the registered rules in turn until the first rule matches.
 * @param message the message
 * @return the content type information for the message or null if none of the rules matches
 * @throws JMSException
 */
public ContentTypeInfo _XXXXX_(Message message) throws JMSException {
  for (  ContentTypeRule rule : rules) {
    ContentTypeInfo contentTypeInfo=rule.getContentType(message);
    if (contentTypeInfo != null) {
      return contentTypeInfo;
    }
  }
  return null;
}
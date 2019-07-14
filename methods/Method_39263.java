/** 
 * Creates  {@link MailSession} {@link Properties}.
 * @return session {@link Properties}
 */
protected Properties createSessionProperties(){
  final Properties props=new Properties();
  props.putAll(customProperties);
  if (debugMode) {
    props.put(MAIL_DEBUG,"true");
  }
  if (!strictAddress) {
    props.put(MAIL_MIME_ADDRESS_STRICT,"false");
  }
  return props;
}

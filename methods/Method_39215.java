/** 
 * Adds plain message text.
 * @param text     The text to add as a {@link String}.
 * @param encoding The encoding as a {@link String}.
 * @return this
 * @see #message(EmailMessage)
 */
public T textMessage(final String text,final String encoding){
  return message(new EmailMessage(text,MimeTypes.MIME_TEXT_PLAIN,encoding));
}

/** 
 * Adds HTML message.
 * @param html The HTML to add as a {@link String}.
 * @return this
 * @see #message(EmailMessage)
 */
public T htmlMessage(final String html){
  return message(new EmailMessage(html,MimeTypes.MIME_TEXT_HTML));
}

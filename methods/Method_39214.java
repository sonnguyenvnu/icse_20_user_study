/** 
 * Adds plain message text.
 * @param text The text to add as a {@link String}.
 * @return this
 * @see #message(String,String)
 */
public T textMessage(final String text){
  return message(text,MimeTypes.MIME_TEXT_PLAIN);
}

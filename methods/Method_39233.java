/** 
 * Returns  {@code true} if attachment is embedded into provided message.
 * @param emailMessage target {@link EmailMessage}.
 * @return {@code true} if attachment is embedded into provided message.
 */
public boolean isEmbeddedInto(final EmailMessage emailMessage){
  return targetMessage == emailMessage;
}

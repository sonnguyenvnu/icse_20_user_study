/** 
 * Returns  {@code true} if the attachment is embedded.<p> Embedded attachment is one when  {@link #getContentId()} is not {@code null}.
 * @return {@code true} if the attachment is embedded.
 */
public boolean isEmbedded(){
  return contentId != null;
}

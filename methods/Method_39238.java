/** 
 * Defines filteer for message number.
 * @param messageNumber The message number.
 * @return this
 */
public EmailFilter messageNumber(final int messageNumber){
  final SearchTerm msgIdTerm=new MessageNumberTerm(messageNumber);
  concat(msgIdTerm);
  return this;
}

/** 
 * Appends BCC address.
 * @param bccs array of {@link Address}es to set.
 * @return this
 * @see #bcc(EmailAddress)
 */
public Email bcc(final Address... bccs){
  return bcc(EmailAddress.of(bccs));
}

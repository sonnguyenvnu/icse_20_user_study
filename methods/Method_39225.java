/** 
 * Appends BCC address.
 * @param bcc {@link Address} to add.
 * @return this
 * @see #bcc(EmailAddress)
 */
public Email bcc(final Address bcc){
  return bcc(EmailAddress.of(bcc));
}

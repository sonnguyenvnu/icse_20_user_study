/** 
 * Defines filter for BCC field.
 * @param bccAddress BCC address.
 * @return this
 */
public EmailFilter bcc(final String bccAddress){
  final SearchTerm toTerm=new RecipientStringTerm(RecipientType.BCC,bccAddress);
  concat(toTerm);
  return this;
}

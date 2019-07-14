/** 
 * Defines filter on a message body. All parts of the message that are of MIME type "text/*" are searched.
 * @param pattern String pattern use in body.
 * @return this
 */
public EmailFilter text(final String pattern){
  final SearchTerm term=new BodyTerm(pattern);
  concat(term);
  return this;
}

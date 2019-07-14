/** 
 * Defines filter for received date.
 * @return this
 */
public EmailFilter receivedDate(final Operator operator,final long milliseconds){
  final SearchTerm term=new ReceivedDateTerm(operator.value,new Date(milliseconds));
  concat(term);
  return this;
}

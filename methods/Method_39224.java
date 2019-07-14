/** 
 * Sets current date as the sent date.
 * @return this
 * @see #sentDate(Date)
 */
public Email currentSentDate(){
  return sentDate(new Date());
}

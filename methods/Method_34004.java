/** 
 * The name of this principal is the consumer key.
 * @return The name of this principal is the consumer key.
 */
public String getName(){
  return getConsumerDetails() != null ? getConsumerDetails().getConsumerKey() : null;
}

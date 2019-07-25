/** 
 * @param messages the messages to be aggregated
 * @return the list as it was passed in
 */
@Aggregator(sendPartialResultsOnExpiry="true") public List<?> aggregate(@Payloads List<?> messages){
  return messages;
}

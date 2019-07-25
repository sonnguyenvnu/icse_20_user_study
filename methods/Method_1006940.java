/** 
 * Send the items one-by-one to the default destination of the JMS template.
 * @see org.springframework.batch.item.ItemWriter#write(java.util.List)
 */
@Override public void write(List<? extends T> items) throws Exception {
  if (logger.isDebugEnabled()) {
    logger.debug("Writing to JMS with " + items.size() + " items.");
  }
  for (  T item : items) {
    jmsTemplate.convertAndSend(item);
  }
}

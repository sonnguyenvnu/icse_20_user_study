/** 
 * Returns a fully constructed  {@link JmsItemWriter}.
 * @return a new {@link JmsItemWriter}
 */
public JmsItemWriter<T> build(){
  Assert.notNull(this.jmsTemplate,"jmsTemplate is required.");
  JmsItemWriter<T> jmsItemWriter=new JmsItemWriter<>();
  jmsItemWriter.setJmsTemplate(this.jmsTemplate);
  return jmsItemWriter;
}

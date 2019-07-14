/** 
 * ????????
 * @param jmsTemplate
 * @param destination
 * @param objectMessage
 * @param delay
 */
public static void sendMessageDelay(JmsTemplate jmsTemplate,Destination destination,final Serializable objectMessage,final long delay){
  jmsTemplate.send(destination,new MessageCreator(){
    @Override public Message createMessage(    Session session) throws JMSException {
      ObjectMessage om=session.createObjectMessage(objectMessage);
      om.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY,delay);
      om.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_PERIOD,1 * 1000);
      om.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_REPEAT,1);
      return om;
    }
  }
);
}

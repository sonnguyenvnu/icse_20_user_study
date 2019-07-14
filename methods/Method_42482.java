/** 
 * ??????Connection?ConnectionFactory????? JMS??????
 * @return ???????
 */
@Bean(name="targetConnectionFactory") public ActiveMQConnectionFactory activeMQConnectionFactory(){
  ActiveMQConnectionFactory activeMQConnectionFactory=new ActiveMQConnectionFactory();
  activeMQConnectionFactory.setBrokerURL(mqBrokerURL);
  activeMQConnectionFactory.setUserName(mqUserName);
  activeMQConnectionFactory.setPassword(mqPassword);
  return activeMQConnectionFactory;
}

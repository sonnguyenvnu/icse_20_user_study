@Bean(destroyMethod="destroy") @ConditionalOnMissingBean public RocketMQTemplate rocketMQTemplate(DefaultMQProducer mqProducer,ObjectMapper objectMapper){
  RocketMQTemplate rocketMQTemplate=new RocketMQTemplate();
  rocketMQTemplate.setProducer(mqProducer);
  rocketMQTemplate.setObjectMapper(objectMapper);
  return rocketMQTemplate;
}

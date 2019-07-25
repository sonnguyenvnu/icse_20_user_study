@Bean("minet") public Queue mine(){
  return new Queue(TOPIC_ROUTING_KEY_MINE);
}

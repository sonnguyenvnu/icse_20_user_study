@Bean("minef") public Queue mine(){
  return new Queue(FANOUT_ROUTING_KEY_MINE);
}

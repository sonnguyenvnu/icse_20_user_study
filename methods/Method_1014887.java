@Bean("objectd") public Queue object(){
  return new Queue(DIRECT_ROUTING_KEY_OBJECT);
}

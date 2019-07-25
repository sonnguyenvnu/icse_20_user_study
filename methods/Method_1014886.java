@Bean("yujiaojiaod") public Queue yujiaojiao(){
  return new Queue(DIRECT_ROUTING_KEY_YUJIAOJIAO);
}

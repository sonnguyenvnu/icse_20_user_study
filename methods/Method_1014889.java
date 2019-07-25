@Bean("yujiaojiaof") public Queue yujiaojiao(){
  return new Queue(FANOUT_ROUTING_KEY_YUJIAOJIAO);
}

@Bean public Binding FanoutBinding1(){
  return BindingBuilder.bind(topicQueue1()).to(fanoutExchage());
}

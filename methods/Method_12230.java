@Bean public Binding topicBinding1(){
  return BindingBuilder.bind(topicQueue1()).to(topicExchage()).with("topic.key1");
}

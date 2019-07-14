@Bean public Binding topicBinding2(){
  return BindingBuilder.bind(topicQueue2()).to(topicExchage()).with("topic.#");
}

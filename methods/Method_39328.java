public void invokeConsumerIfRegistered(){
  if (beanDefinition.consumer() == null) {
    return;
  }
  beanDefinition.consumer().accept(bean);
}

private void copyCommon(ConsumerConfig<T> consumerConfig,ReferenceConfig<T> referenceConfig){
  referenceConfig.setInjvm(consumerConfig.isInJVM());
}

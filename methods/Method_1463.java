public <T>ThrottlingProducer<T> newThrottlingProducer(Producer<T> inputProducer){
  return new ThrottlingProducer<T>(MAX_SIMULTANEOUS_REQUESTS,mExecutorSupplier.forLightweightBackgroundTasks(),inputProducer);
}

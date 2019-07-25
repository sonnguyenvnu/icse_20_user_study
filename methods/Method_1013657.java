@PostConstruct public void start(){
  logger.info("Alert Notification Manager started");
  eventBus=new AlertEventBus(executors);
  subscribers.forEach(subscriber -> subscriber.register(eventBus));
}

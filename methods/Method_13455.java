@Before(REGISTER_POINTCUT_EXPRESSION) public void beforeRegister(Registration registration){
  applicationEventPublisher.publishEvent(new ServiceInstancePreRegisteredEvent(registration));
}
